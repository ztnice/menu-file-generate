package cn.tablego.project.springboot.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.tablego.project.springboot.model.*;
import cn.tablego.project.springboot.model.condition.TsysMenuCondition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author haozt
 * @date 2021/12/7
 */
@Service
@AllArgsConstructor
@Slf4j
public class SqlGenService {


    private final TsysMenuService tsysMenuService;
    private final TsysTransService tsysTransService;
    private final TsysSubtransService tsysSubtransService;
    private final TsysRoleRightService tsysRoleRightService;
    private final TsysUserRightService tsysUserRightService;


    private static final String PATH = "D:\\Users\\hundsun\\risk3.0\\菜单脚本";
    private static final String SPACE = "\t";
    private static final String SINGLE_QUOTE = "'";
    private static final String LEFT = "VALUES (";
    private static final String RIGHT = ")";
    private static final String SEMICOLON = ";";

    private static final String NEXT_LINE = "\n";
    private static final String NULL = "null";
    private static final String COMMA = ",";

    public boolean genSqlFile(String menuCodes) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            String[] menuCodeArr = menuCodes.split(",");
            String inMenuCodes = inMenuCodes(menuCodeArr);
            //delSql
            genDelSql(inMenuCodes, stringBuilder);

            for (String menuCode : menuCodeArr) {
                TsysTrans tsysTrans = tsysTransService.getTsysTransById(menuCode);
                TsysMenu tsysMenu = tsysMenuService.getTsysMenuById(menuCode);
                List<TsysSubtrans> tsysSubtrans = tsysSubtransService.listTsysSubtrans(menuCode);
                if (tsysTrans == null || tsysMenu == null || CollUtil.isEmpty(tsysSubtrans)) {
                    log.info("数据存在错误，参数信息{}，TSYS_TRANS表结果{}，TSYS_MENU表结果{}，TSYS_SUBTRANS表结果{}",
                            menuCode, tsysMenu, tsysTrans, tsysSubtrans == null ? "null" : tsysSubtrans.isEmpty());
                    continue;
                }

                //生成sql tsys_trans
                tSysTransSql(stringBuilder, tsysTrans);

                //tsys_subtrans
                tSysSubTransSql(stringBuilder, tsysSubtrans);

                //tsys_menu
                tSysMenuSql(stringBuilder, tsysMenu);

                //tsys_user_right
                List<TsysUserRight> userRights = tsysUserRightService.listUserRights(menuCode);
                tSysUserRightSql(stringBuilder, userRights);
                //tsys_role_right
                List<TsysRoleRight> roleRights = tsysRoleRightService.listRoleRights(menuCode);
                tSysRoleRightSql(stringBuilder, roleRights);
            }

            writeFileToLocal(stringBuilder.toString());
            return true;
        }catch (Exception e){
            log.error("生成文件出现错误",e);
            return false;
        }

    }



    public boolean genChildrenSqlFile(String parentMenuCode){
        return genSqlFile(genChildMenuCodes(parentMenuCode));
    }



    public boolean genDelChildrenSqlFile(String parentMenuCode){
        return genDelSqlFile(genChildMenuCodes(parentMenuCode));
    }


    public boolean genDelSqlFile(String menuCodes){
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String[] menuCodeArr = menuCodes.split(",");
            String inMenuCodes = inMenuCodes(menuCodeArr);
            //delSql
            genDelSql(inMenuCodes, stringBuilder);
            writeFileToLocal(stringBuilder.toString());
            return true;
        }catch (Exception e){
            log.error("生成删除脚本出现错误",e);
            return false;
        }
    }


    private String genChildMenuCodes(String parentMenuCode){
        TsysMenuCondition tsysMenuCondition = TsysMenuCondition.newInstance();

        QueryWrapper<TsysMenu> queryWrapper = tsysMenuCondition.buildQueryWrapper();
        queryWrapper.eq("PARENT_CODE",parentMenuCode);
        List<TsysMenu> tsysMenuList = tsysMenuService.findTsysMenu(queryWrapper);

        StringBuilder stringBuilder = new StringBuilder(parentMenuCode).append(COMMA);

        Optional.ofNullable(tsysMenuList).ifPresent(tsysMenus -> {
            tsysMenus.forEach(tsysMenu -> {
                stringBuilder.append(tsysMenu.getMenuCode()).append(COMMA);
            });
        });

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(COMMA));

        return stringBuilder.toString();

    }

    private void tSysMenuSql(StringBuilder stringBuilder, TsysMenu tsysMenu) {
        stringBuilder.append("insert into TSYS_MENU (menu_code, kind_code, trans_code, sub_trans_code, " +
                "menu_name, menu_arg, menu_icon, menu_url, window_type, window_model, tip, hot_key," +
                " parent_code, order_no, open_flag, tree_idx, remark)");

        stringBuilder.append(NEXT_LINE).append(LEFT).append(SINGLE_QUOTE).append(tsysMenu.getMenuCode()).append(SINGLE_QUOTE)
                .append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getKindCode()).append(SINGLE_QUOTE)
                .append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getTransCode()).append(SINGLE_QUOTE)
                .append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getSubTransCode()).append(SINGLE_QUOTE)
                .append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getMenuName()).append(SINGLE_QUOTE)
                .append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getMenuArg()).append(SINGLE_QUOTE)
                .append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getMenuIcon()).append(SINGLE_QUOTE)
                .append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getMenuUrl()).append(SINGLE_QUOTE)
                .append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getWindowType()).append(SINGLE_QUOTE)
                .append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getWindowModel()).append(SINGLE_QUOTE).append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getTip()).append(SINGLE_QUOTE).append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getHotKey()).append(SINGLE_QUOTE).append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getParentCode()).append(SINGLE_QUOTE).append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getOrderNo()).append(SINGLE_QUOTE).append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getOpenFlag()).append(SINGLE_QUOTE).append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getTreeIdx()).append(SINGLE_QUOTE).append(COMMA)
                .append(SPACE).append(SINGLE_QUOTE).append(tsysMenu.getRemark()).append(SINGLE_QUOTE)
                .append(RIGHT).append(SEMICOLON).append(NEXT_LINE);
        appendCommit(stringBuilder);
    }


    private void tSysTransSql(StringBuilder stringBuilder, TsysTrans tsysTrans) {
        stringBuilder.append("insert into TSYS_TRANS (trans_code, trans_name, kind_code, model_code, remark, ext_field_1, ext_field_2, ext_field_3)");
        stringBuilder.append(NEXT_LINE).append(LEFT).append(SINGLE_QUOTE).append(tsysTrans.getTransCode()).append(SINGLE_QUOTE)
                .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(tsysTrans.getTransName()).append(SINGLE_QUOTE)
                .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(tsysTrans.getKindCode()).append(SINGLE_QUOTE)
                .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(tsysTrans.getModelCode()).append(SINGLE_QUOTE)
                .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(tsysTrans.getRemark()).append(SINGLE_QUOTE)
                .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(tsysTrans.getExtField1()).append(SINGLE_QUOTE)
                .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(tsysTrans.getExtField2()).append(SINGLE_QUOTE)
                .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(tsysTrans.getExtField3()).append(SINGLE_QUOTE)
                .append(RIGHT).append(SEMICOLON).append(NEXT_LINE);
        appendCommit(stringBuilder);
    }

    private void tSysSubTransSql(StringBuilder stringBuilder, List<TsysSubtrans> tsysSubtransList) {
        Optional.ofNullable(tsysSubtransList).ifPresent(tsysSubtrans -> {
            tsysSubtrans.forEach(subtrans -> {
                stringBuilder.append("insert into TSYS_SUBTRANS (trans_code, sub_trans_code, sub_trans_name," +
                        " rel_serv, rel_url, ctrl_flag, login_flag, remark, ext_field_1, ext_field_2, ext_field_3)");
                stringBuilder.append(NEXT_LINE).append(LEFT).append(SINGLE_QUOTE).append(subtrans.getTransCode()).append(SINGLE_QUOTE)
                        .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(subtrans.getSubTransCode()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(subtrans.getSubTransName()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(subtrans.getRelServ()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(subtrans.getRelUrl()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(subtrans.getCtrlFlag()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(subtrans.getLoginFlag()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(subtrans.getRemark()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(subtrans.getExtField1()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(subtrans.getExtField2()).append(SINGLE_QUOTE)
                        .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(subtrans.getExtField3()).append(SINGLE_QUOTE)
                        .append(RIGHT).append(SEMICOLON).append(NEXT_LINE);


            });
            appendCommit(stringBuilder);
        });
    }

    private void tSysRoleRightSql(StringBuilder stringBuilder, List<TsysRoleRight> tsysRoleRights) {

        Optional.ofNullable(tsysRoleRights).ifPresent(roleRights -> {
            roleRights.forEach(roleRight -> {
                stringBuilder.append("insert into TSYS_ROLE_RIGHT (trans_code, sub_trans_code, role_code, " +
                        "create_by, create_date, begin_date, end_date, right_flag, right_enable)");
                stringBuilder.append(NEXT_LINE).append(LEFT).append(SINGLE_QUOTE).append(roleRight.getTransCode()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(roleRight.getSubTransCode()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(roleRight.getRoleCode()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(roleRight.getCreateBy()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(roleRight.getCreateDate()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(roleRight.getBeginDate()).append(SINGLE_QUOTE)
                        .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(roleRight.getEndDate()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(roleRight.getRightFlag()).append(SINGLE_QUOTE)
                        .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(roleRight.getRightEnable()).append(SINGLE_QUOTE)
                        .append(RIGHT).append(SEMICOLON).append(NEXT_LINE);


            });
            appendCommit(stringBuilder);
        });
    }

    private void tSysUserRightSql(StringBuilder stringBuilder, List<TsysUserRight> tsysUserRights) {
        Optional.ofNullable(tsysUserRights).ifPresent(userRights -> {
            userRights.forEach(userRight -> {
                stringBuilder.append("insert into TSYS_USER_RIGHT (trans_code, sub_trans_code, user_id, " +
                        "create_by, create_date, begin_date, end_date, right_flag, right_enable)");
                stringBuilder.append(NEXT_LINE).append(LEFT).append(SINGLE_QUOTE).append(userRight.getTransCode()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(userRight.getSubTransCode()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(userRight.getUserId()).append(SINGLE_QUOTE)
                        .append(COMMA) .append(SPACE).append(SINGLE_QUOTE).append(userRight.getCreateBy()).append(SINGLE_QUOTE)
                        .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(userRight.getCreateDate()).append(SINGLE_QUOTE)
                        .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(userRight.getBeginDate()).append(SINGLE_QUOTE)
                        .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(userRight.getEndDate()).append(SINGLE_QUOTE)
                        .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(userRight.getRightFlag()).append(SINGLE_QUOTE)
                        .append(COMMA).append(SPACE).append(SINGLE_QUOTE).append(userRight.getRightEnable()).append(SINGLE_QUOTE)
                        .append(RIGHT).append(SEMICOLON).append(NEXT_LINE);


            });
            appendCommit(stringBuilder);
        });
    }


    private void genDelSql(String menuCodes, StringBuilder stringBuilder) {
        stringBuilder.append("delete from TSYS_MENU where menu_code in (")
                .append(menuCodes).append(")").append(SEMICOLON).append(NEXT_LINE);
        stringBuilder.append("delete from TSYS_USER_RIGHT where trans_code in (")
                .append(menuCodes).append(")").append(SEMICOLON).append(NEXT_LINE);
        stringBuilder.append("delete from TSYS_ROLE_RIGHT where trans_code in (")
                .append(menuCodes).append(")").append(SEMICOLON).append(NEXT_LINE);
        stringBuilder.append("delete from TSYS_SUBTRANS where trans_code in (")
                .append(menuCodes).append(")").append(SEMICOLON).append(NEXT_LINE);
        stringBuilder.append("delete from TSYS_TRANS where trans_code in (")
                .append(menuCodes).append(")").append(SEMICOLON).append(NEXT_LINE);
        stringBuilder.append("commit").append(SEMICOLON).append(NEXT_LINE);
    }


    private String inMenuCodes(String[] menuCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String menuCode : menuCodes) {
            stringBuilder.append(SINGLE_QUOTE).append(menuCode).append(SINGLE_QUOTE).append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        return stringBuilder.toString();
    }


    private void  writeFileToLocal(String fileContent){
        String date = DateUtil.format(new Date(),"yyyy-MM-dd-HHmmss");
        long timeStr = System.currentTimeMillis();
        String fileTitle = date + "-"+ timeStr + ".sql";
        String filePath = PATH + File.separator + fileTitle;

        if(fileContent.contains(NULL)){
            fileContent = fileContent.replaceAll(NULL,"");
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("生成文件出现错误",e);
        }

    }

    private void appendCommit(StringBuilder stringBuilder){
        stringBuilder.append("commit").append(SEMICOLON).append(NEXT_LINE).append(NEXT_LINE);
    }
}

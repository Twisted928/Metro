package com.metro.ccms.common.core.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.metro.ccms.common.core.domain.entity.SysBasicData;
import com.metro.ccms.common.core.domain.entity.SysDept;
import com.metro.ccms.common.core.domain.entity.SysMenu;

/**
 * Treeselect树结构实体类
 * 
 * @author ruoyi
 */
public class TreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long value;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    /** 节点ID */
    private String deptCode;

    public TreeSelect()
    {

    }

    public TreeSelect(SysDept dept)
    {
        this.value = dept.getDeptId();
        this.deptCode = dept.getDeptCode();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysMenu menu)
    {
        this.value = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysBasicData sysBasicData)
    {
        this.value = sysBasicData.getId();
        this.deptCode = sysBasicData.getCtype();
        this.label = sysBasicData.getDescription();
        this.children = sysBasicData.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<TreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelect> children)
    {
        this.children = children;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}

package com.ujiuye.pojo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MenuTree extends Model {
    private Integer id;
    private String label;
    private String icon;
    private List<MenuTree> children;

    public MenuTree(Integer id, String label,String icon) {
        this.id = id;
        this.label = label;
        this.icon=icon;
    }
}

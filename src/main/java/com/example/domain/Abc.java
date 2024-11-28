package com.example.domain;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import oracle.net.aso.l;

@Data
@TableName(value = "T_ABC")
// @KeySequence(value = "SEQ_HANDOVER_LOG")
public class Abc {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long templateId;

    private String title;

    // 检查所见 诊断印象
    private int type;

    private int subType;

    private String JsKey;

    private String key;

    private String[] valList;

    // 1 文字输入框 2 选项框 3 数值框

    private String unit;

    private String userInput;

    public Abc() {
    }

    public Abc(Long id, Long templateId, String title, int type, int subType, String jsKey, String key,
            String[] valList,
            String unit) {
        this.id = id;
        this.templateId = templateId;
        this.title = title;
        this.type = type;
        JsKey = jsKey;
        this.key = key;
        this.valList = valList;
        this.subType = subType;
        this.unit = unit;
    }

    public static void main(String[] args) {
        JSONArray list = new JSONArray();
        list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey", "key",
                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

        list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey", "key",
                new String[] { "近段", "中段", "远段" }, ""));

        list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey", "key",
                new String[] { "局限性", "节段性", "弥漫性" }, ""));

        list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey", "key",
                new String[] { "低密度", "正性重构", "“餐巾环”症", "点状钙化" }, ""));
        list.add(new Abc(1L, 1L, "LM", 1, 3, "Jskey", "斑块长度:",
                new String[] {}, "mm"));
        list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey", "管腔狭窄:",
                new String[] { "无狭窄", "轻微", "轻度", "中度", "重度", "闭塞" }, ""));

        System.out.println(JSON.toJSONString(list));
    }

}

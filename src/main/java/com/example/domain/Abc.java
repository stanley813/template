package com.example.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@TableName(value = "T_ABC")
// @KeySequence(value = "SEQ_HANDOVER_LOG")
public class Abc {
        @TableId(type = IdType.AUTO)
        private Long id;

        private Long templateId;

        private String title;

        // 1 检查所见 2 诊断印象
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

                /*
                 * // 1 文字输入框 2 选项框 3 数值框
                 * 
                 */

                list.add(new Abc(1L, 1L, "冠状动脉钙化总积分", 1, 3, "Jskey_1", "",
                                new String[] {}, ""));

                list.add(new Abc(1L, 1L, "冠状动脉钙化总积分", 1, 3, "Jskey_2", "LM",
                                new String[] {}, ""));

                list.add(new Abc(1L, 1L, "冠状动脉钙化总积分", 1, 3, "Jskey_3", "LDA",
                                new String[] {}, ""));

                list.add(new Abc(1L, 1L, "冠状动脉钙化总积分", 1, 3, "Jskey_4", "LCX",
                                new String[] {}, ""));

                list.add(new Abc(1L, 1L, "冠状动脉钙化总积分", 1, 3, "Jskey_5", "RCA",
                                new String[] {}, ""));

                list.add(new Abc(1L, 1L, "冠状动脉起源、走行及终止", 1, 4, "Jskey_6", "",
                                new String[] { "正常", "异常" }, ""));

                list.add(new Abc(1L, 1L, "冠状动脉起源、走行及终止", 1, 1, "Jskey_7", "",
                                new String[] {}, ""));

                list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey_8", "",
                                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

                list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey_9", "",
                                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

                list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey_10", "",
                                new String[] { "近段", "中段", "远段" }, ""));

                list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey_11", "",
                                new String[] { "局限性", "节段性", "弥漫性" }, ""));

                list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey_12", "",
                                new String[] { "低密度", "正性重构", "“餐巾环”症", "点状钙化" }, ""));
                list.add(new Abc(1L, 1L, "LM", 1, 3, "Jskey", "斑块长度:",
                                new String[] {}, "mm"));
                list.add(new Abc(1L, 1L, "LM", 1, 2, "Jskey_13", "管腔狭窄:",
                                new String[] { "无狭窄", "轻微", "轻度", "中度", "重度", "闭塞" }, ""));

                list.add(new Abc(1L, 1L, "LAD", 1, 2, "Jskey_14", "",
                                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

                list.add(new Abc(1L, 1L, "LAD", 1, 2, "Jskey_15", "",
                                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

                list.add(new Abc(1L, 1L, "LAD", 1, 2, "Jskey_16", "",
                                new String[] { "近段", "中段", "远段" }, ""));

                list.add(new Abc(1L, 1L, "LAD", 1, 2, "Jskey_17", "",
                                new String[] { "局限性", "节段性", "弥漫性" }, ""));

                list.add(new Abc(1L, 1L, "LAD", 1, 2, "Jskey_18", "",
                                new String[] { "低密度", "正性重构", "“餐巾环”症", "点状钙化" }, ""));
                list.add(new Abc(1L, 1L, "LAD", 1, 3, "Jskey", "斑块长度:",
                                new String[] {}, "mm"));
                list.add(new Abc(1L, 1L, "LAD", 1, 2, "Jskey_19", "管腔狭窄:",
                                new String[] { "无狭窄", "轻微", "轻度", "中度", "重度", "闭塞" }, ""));

                list.add(new Abc(1L, 1L, "LCX", 1, 2, "Jskey_20", "",
                                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

                list.add(new Abc(1L, 1L, "LCX", 1, 2, "Jskey_21", "",
                                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

                list.add(new Abc(1L, 1L, "LCX", 1, 2, "Jskey_22", "",
                                new String[] { "近段", "中段", "远段" }, ""));

                list.add(new Abc(1L, 1L, "LCX", 1, 2, "Jskey_23", "",
                                new String[] { "局限性", "节段性", "弥漫性" }, ""));

                list.add(new Abc(1L, 1L, "LCX", 1, 2, "Jskey_24", "",
                                new String[] { "低密度", "正性重构", "“餐巾环”症", "点状钙化" }, ""));
                list.add(new Abc(1L, 1L, "LCX", 1, 3, "Jskey_25", "斑块长度:",
                                new String[] {}, "mm"));
                list.add(new Abc(1L, 1L, "LCX", 1, 2, "Jskey_26", "管腔狭窄:",
                                new String[] { "无狭窄", "轻微", "轻度", "中度", "重度", "闭塞" }, ""));

                list.add(new Abc(1L, 1L, "RCA", 1, 2, "Jskey_27", "",
                                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

                list.add(new Abc(1L, 1L, "RCA", 1, 2, "Jskey_28", "",
                                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

                list.add(new Abc(1L, 1L, "RCA", 1, 2, "Jskey_29", "",
                                new String[] { "近段", "中段", "远段" }, ""));

                list.add(new Abc(1L, 1L, "RCA", 1, 2, "Jskey_30", "",
                                new String[] { "局限性", "节段性", "弥漫性" }, ""));

                list.add(new Abc(1L, 1L, "RCA", 1, 2, "Jskey_31", "",
                                new String[] { "低密度", "正性重构", "“餐巾环”症", "点状钙化" }, ""));
                list.add(new Abc(1L, 1L, "RCA", 1, 3, "Jskey_32", "斑块长度:",
                                new String[] {}, "mm"));
                list.add(new Abc(1L, 1L, "RCA", 1, 2, "Jskey_33", "管腔狭窄:",
                                new String[] { "无狭窄", "轻微", "轻度", "中度", "重度", "闭塞" }, ""));

                // 其余分支：D1/D2/RI/OM1/OM2/L-PDA/R-PDA/L-PLB/R-PLB; 无斑块/钙化板块/非钙化板块/混合斑块;
                // 近段/中段/远段；局限性/节段性/弥漫性；低密度/正性重构/“餐巾环”症/点状钙化；斑块长度5 mm；管腔狭窄：无狭窄/轻微/轻度/中度/重度/闭塞。

                list.add(new Abc(1L, 1L, "其余分支", 1, 2, "Jskey_34", "",
                                new String[] { "D1", "D2", "RI", "OM1", "OM2", "L-PDA", "R-PDA", "L-PLB", "R-PLB" },
                                ""));

                list.add(new Abc(1L, 1L, "其余分支", 1, 2, "Jskey_35", "",
                                new String[] { "无斑块", "钙化板块", "非钙化板块", "混合斑块" }, ""));

                list.add(new Abc(1L, 1L, "其余分支", 1, 2, "Jskey_36", "",
                                new String[] { "近段", "中段", "远段" }, ""));
                list.add(new Abc(1L, 1L, "其余分支", 1, 2, "Jskey_37", "",
                                new String[] { "局限性", "节段性", "弥漫性" }, ""));
                list.add(new Abc(1L, 1L, "其余分支", 1, 2, "Jskey_37", "",
                                new String[] { "低密度", "正性重构", "“餐巾环”症", "点状钙化" }, ""));
                list.add(new Abc(1L, 1L, "其余分支", 1, 3, "Jskey_38", "斑块长度:",
                                new String[] {}, "mm"));
                list.add(new Abc(1L, 1L, "其余分支", 1, 2, "Jskey_39", "管腔狭窄:",
                                new String[] { "无狭窄", "轻微", "轻度", "中度", "重度", "闭塞" }, ""));

                /*
                 * 
                 * 各心房心室结构大小及关系正常，未见明显异常沟通及连接；房室腔内未见明显异常充盈缺损影。心肌密度未见明显异常，心肌未见明显增厚或变薄。
                 * 心脏瓣膜未见明显增厚及钙化影。心包未见明显增厚、钙化及积液。
                 * 扫及范围内心外结构未见明显异常。
                 */

                /*
                 * 1．冠状动脉钙化总积分：187.52。
                 */
                list.add(new Abc(1L, 1L, "冠状动脉钙化总积分", 2, 3, "Jskey", "",
                                new String[] {}, ""));

                /*
                 * 2．CAD-RADS 3/HRP；左前降支(LAD)近段混合斑块(高危斑块),管腔中度狭窄。
                 */

                // TODO： 遗漏
                log.info(JSON.toJSONString(JSON.toJSONString(list,
                                SerializerFeature.WriteMapNullValue,
                                SerializerFeature.WriteNullStringAsEmpty,
                                SerializerFeature.WriteNullListAsEmpty)));
        }

}

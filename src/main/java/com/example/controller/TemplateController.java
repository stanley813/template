package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import oracle.net.aso.s;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.Abc;
import com.example.domain.Result;
import com.example.domain.Template;
import com.example.service.TemplateService;

/**
 * Created by Stanley Zhou on 2019/7/9.
 */
@RestController
@RequestMapping("template")
@Slf4j
public class TemplateController {

    private static final Long PARENT_ID = 0l;

    @Autowired
    TemplateService templateService;

    @GetMapping("/tree")
    public ResponseEntity<JSONArray> getTemplateTree() {

        log.info("getTemplateTree");
        try {
            List<Template> list = templateService.list();
            JSONArray templateTree = getTemplateTree(list, PARENT_ID);
            return ResponseEntity.status(HttpStatus.OK).body(templateTree);
        } catch (Exception e) {
            log.error("getTemplateTree error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Template>> getTemplate() {

        log.info("getTemplate");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(templateService.list());
        } catch (Exception e) {
            log.error("getTemplate error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping()
    public ResponseEntity<Void> addTemplate(@RequestBody Template template) {

        log.info("addTemplate:{}", JSON.toJSONString(template));
        try {
            template.setCreateTime(new Date());
            templateService.save(template);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("addTemplate error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping()
    public ResponseEntity<Void> updateTemplate(@RequestBody Template template) {

        log.info("updateTemplate:{}", JSON.toJSONString(template));
        try {
            templateService.updateById(template);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("updateTemplate error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/getHtmlTemplate")
    public ResponseEntity<Template> getHtmlTemplate(@RequestBody Template template) {

        log.info("getHtmlTemplate:{}", JSON.toJSONString(template));
        try {
            Template t = templateService.getById(template.getId());
            String content = t.getContent();
            // return ResponseEntity.status(HttpStatus.OK).body(JSON.parseArray(content));
            return ResponseEntity.status(HttpStatus.OK).body(t);
        } catch (Exception e) {
            log.error("getHtmlTemplate error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveResult(@RequestBody List<Abc> abcList) {

        log.info("saveResult:{}", JSON.toJSONString(abcList));
        try {

            Map<String, String> checkLhm = new LinkedHashMap<>();
            Map<String, String> conclusionLhm = new LinkedHashMap<>();

            for (int i = 0; i < abcList.size(); i++) {
                Abc abc = abcList.get(i);
                String title = abc.getTitle();
                String sentence = "";
                String key = "";
                if (StringUtils.isNotEmpty(abc.getKey())) {
                    key = abc.getKey();
                }
                if (abc.getType() == 1) {
                    sentence = checkLhm.get(title);
                    if (StringUtils.isEmpty(sentence)) {
                        sentence = abc.getTitle() + ":" + abc.getUserInput();
                        checkLhm.put(title, sentence);
                        continue;
                    }
                    sentence = sentence + ";" + key + abc.getUserInput();
                    if (StringUtils.isNoneEmpty(abc.getUnit()))
                        sentence += abc.getUnit();
                    checkLhm.put(title, sentence);
                } else if (abc.getType() == 2) {
                    sentence = conclusionLhm.get(title);
                    if (StringUtils.isEmpty(sentence)) {
                        sentence = abc.getTitle() + ":" + abc.getUserInput();
                        conclusionLhm.put(title, sentence);
                        continue;
                    }
                    sentence = sentence + ";" + key + abc.getUserInput();
                    if (StringUtils.isNoneEmpty(abc.getUnit()))
                        sentence += abc.getUnit();
                    conclusionLhm.put(title, sentence);
                }
            }
            Result result = new Result();
            result.setCheck(loopMap(checkLhm));
            result.setConclusion(loopMap(conclusionLhm));
            result.setId(1L);
            log.info(JSON.toJSONString(result));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("saveResult error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String loopMap(Map<String, String> map) {
        String s = "";
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            s += entry.getValue() + "\\r ";
        }
        return s;
    }

    @Deprecated
    @PostMapping("/getTextFormat")
    public ResponseEntity<JSONObject> getTextFormat(@RequestBody Template template) {

        log.info("getTextFormat:{}", JSON.toJSONString(template));
        try {
            JSONObject jsonObject = new JSONObject();

            Template t = templateService.getById(template.getId());
            String content = t.getContent();

            List<Abc> abcList = JSON.parseArray(content, Abc.class);
            Map<String, JSONArray> checkLhm = new LinkedHashMap<>();
            Map<String, JSONArray> conclusionLhm = new LinkedHashMap<>();
            jsonObject.put("check", checkLhm);
            jsonObject.put("conclusion", conclusionLhm);
            ;

            for (Abc abc : abcList) {
                String title = abc.getTitle();
                if (abc.getType() == 1) {
                    // 检查所见
                    JSONArray jsonArray = checkLhm.get(title);
                    if (CollectionUtils.isEmpty(checkLhm.get(title))) {
                        jsonArray = new JSONArray();
                    }
                    jsonArray.add(abc);
                    checkLhm.put(title, jsonArray);

                } else if (abc.getType() == 2) {
                    // 诊断印象
                    JSONArray jsonArray = checkLhm.get(title);
                    if (CollectionUtils.isEmpty(checkLhm.get(title))) {
                        jsonArray = new JSONArray();
                    }
                    jsonArray.add(abc);
                    conclusionLhm.put(title, jsonArray);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(jsonObject);
        } catch (Exception e) {
            log.error("getTextFormat error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private JSONArray getTemplateTree(List<Template> templateList, Long parentDeptId) {
        JSONArray jsonArray = new JSONArray();
        for (Template template : templateList) {
            if (template.getParentId().equals(parentDeptId)) {
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(template);
                JSONArray templateTree = getTemplateTree(templateList, template.getId());
                if (!CollectionUtils.isEmpty(templateTree)) {
                    jsonObject.put("nodes", templateTree);
                }
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }

    private void handle(Abc abc) {
        // 1 文字输入框 2 选项框 3 数值框
        if (abc.getSubType() == 1) {

        } else if (abc.getSubType() == 2) {

        } else if (abc.getSubType() == 3) {

        } else if (abc.getSubType() == 4) {

        } else {

        }

    }

    /*
     * 
     * public static JSONArray transform(String text) {
     * 
     * // | 代表 作为 报告名称 、检查所见、诊断印象的分隔符
     * // && 代表每一行的分隔符
     * // ; 代表每一行中各个参数的分隔符
     * // : 代表key、value间的分隔符
     * // ^ 代表每一行标题与内容的分隔符
     * // / 代表多选框
     * // $$ 代表数值框
     * // %% 代表文本框
     * 
     * 
     * String[] a = text.split("\\|");
     * String reportName = a[0];
     * 
     * String finding = a[1];
     * String[] findingArray = finding.split("&&");
     * List<Abc> abcList = new ArrayList<>();for(
     * String b:findingArray)
     * {
     * String[] split = b.split("\\^");
     * Abc abc = new Abc();
     * abc.setTitle(split[0]);
     * 
     * String c = split[1];
     * String[] split2 = c.split(";");
     * List<Cba> cbaList = new ArrayList<>();
     * for (String d : split2) {
     * 
     * Cba cba = new Cba();
     * 
     * int type = 0;
     * if (d.contains(":")) {
     * String[] e = d.split(":");
     * cba.setKey(e[0]);
     * d = e[1];
     * }
     * if (d.contains("/")) {
     * type = 1;
     * cba.setValList(Arrays.asList(d.split("/")));
     * } else if (d.contains("$$")) {
     * type = 2;
     * } else if (d.contains("%%")) {
     * type = 3;
     * } else {
     * type = 4;
     * }
     * cba.setType(type);
     * cbaList.add(cba);
     * // abc.setContent(cbaList);
     * }
     * abcList.add(abc);
     * }
     * String impression = a[2];
     * 
     * return null;
     * }
     * 
     * private JSONArray transform2(String text) {
     * 
     * // 检查所见部分
     * JSONArray result = new JSONArray();
     * String[] sa1 = text.split("。");
     * for (String s1 : sa1) {
     * String key = s1.substring(0, s1.indexOf(":"));
     * JSONObject a = new JSONObject();
     * JSONArray b = new JSONArray();
     * 
     * String[] sa2 = s1.substring(s1.indexOf(":") + 1).split(";");
     * for (String s2 : sa2) {
     * if (s2.contains(":")) {
     * JSONObject c = new JSONObject();
     * c.put(s2.substring(0, s2.indexOf(":")),
     * Arrays.asList(s2.substring(s2.indexOf(":") + 1).split(";")));
     * b.add(c);
     * } else {
     * b.add(Arrays.asList(s2.split("/")));
     * }
     * a.put(key, b);
     * }
     * result.add(a);
     * }
     * return result;
     * }
     * 
     * public static void main(String[] args) {
     * String text =
     * "冠状动脉CTA诊断报告|冠状动脉钙化总积分^$$;LM:$$;LDA:$$;LCX:$$;RCA:$$;无斑块/钙化板块/非钙化板块/混合斑块;管腔狭窄:无狭窄/轻微/轻度/中度/重度/闭塞&&冠状动脉起源、走行及终止^正常/异常;右冠优势性/左冠优势性/均衡型|冠状动脉钙化总积分:$$";
     * String[] a = text.split("\\|");
     * String reportName = a[0];
     * 
     * String finding = a[1];
     * String[] findingArray = finding.split("&&");
     * List<Abc> abcList = new ArrayList<>();
     * for (String b : findingArray) {
     * String[] split = b.split("\\^");
     * Abc abc = new Abc();
     * abc.setTitle(split[0]);
     * 
     * String c = split[1];
     * String[] split2 = c.split(";");
     * List<Cba> cbaList = new ArrayList<>();
     * for (String d : split2) {
     * 
     * Cba cba = new Cba();
     * 
     * int type = 0;
     * if (d.contains(":")) {
     * String[] e = d.split(":");
     * cba.setKey(e[0]);
     * d = e[1];
     * }
     * if (d.contains("/")) {
     * type = 1;
     * cba.setValList(Arrays.asList(d.split("/")));
     * } else if (d.contains("$$")) {
     * type = 2;
     * } else if (d.contains("%%")) {
     * type = 3;
     * } else {
     * type = 4;
     * }
     * cba.setType(type);
     * cbaList.add(cba);
     * // abc.setContent(cbaList);
     * }
     * abcList.add(abc);
     * }
     * String impression = a[2];
     * 
     * }
     */
}

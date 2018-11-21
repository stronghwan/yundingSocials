package cn.yunding.social.utils;


import cn.yunding.social.pojo.FriendApply;
import cn.yunding.social.pojo.myPojo.FriendInfo;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @anthor : songwanqing
 * @createtime : 2018-08-02-23:31
 * @discription : 给List表按对象的一个属性排序的方法
 *                  中文转化成拼音的方法
 */
public class ListSorting {

    /**
     * 按list中对象的某个属性给对象排序的方法（昵称）
     * 但是，这里因为还涉及到好友备注的问题
     * 所以 新设一个属性来排序，有备注就显示为备注，没备注就显示昵称
     * @param ls
     */
    public static void sortStringMethod(List ls){
        Collections.sort(ls, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                FriendInfo stu1 = (FriendInfo) o1;
                FriendInfo stu2 = (FriendInfo) o2;
                return stu1.getDisplay().compareTo(stu2.getDisplay());
            }

        });
    }

    /**
     * 按list中对象的某个属性给对象排序的方法（创建时间）
     * compareTo()方法 返回的是0，-1,1,所以乘个(-1)解决倒序问题
     * @param ls
     */
    public static void sortStringMethods(List ls){
        Collections.sort(ls, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                FriendApply stu1 = (FriendApply) o1;
                FriendApply stu2 = (FriendApply) o2;
                return (-1)*stu1.getApplyTime().compareTo(stu2.getApplyTime());
            }
        });
    }

    /**
     * 传入已经排好序的List集合
     * 选出非中文的放在 #组里
     * 剩下的遍历 A~Z存储
     * @param list
     */
    public static Map<String,List<FriendInfo>> GroupByInitials(List<FriendInfo> list){

        /**
         * 先创建分组的这个Map集
         */
        Map<String,List<FriendInfo>> map = new HashMap<String,List<FriendInfo>>();

        /**
         * 排除所有开头非汉字的display(导入Maven包),存入Map<#,list<FriendInfo>>
         */
        //放中文字符开头的friendList
        List<FriendInfo> ls1 = new ArrayList<FriendInfo>();
        //放开头是乱码的friendList
        List<FriendInfo> ls2 = new ArrayList<FriendInfo>();

        //分成两个List
        for (int i = 0; i < list.size(); i++) {
            if(FirstCharacterIsChisese(list.get(i).getDisplay())
                    || FirstCharacterIsEnglish(list.get(i).getDisplay())){
                ls1.add(list.get(i));
            } else {
                ls2.add(list.get(i));
            }
        }

        //把非中文ls1放进 #组里
        sortStringMethod(ls2);
        map.put("#",ls2);

        //先遍历A~Z的ASCII码值
        for (char x = 'A'; x <= 'Z' ; x++) {
            List<FriendInfo> ls = new ArrayList<FriendInfo>();
            for (int j = 0; j < ls1.size(); j++) {
                //得到所有中文开头display的首字母
                if(x==getPinYinHeadChar(ls1.get(j).getDisplay()).toUpperCase().charAt(0)){
                    ls.add(ls1.get(j));
                }
            }
            sortStringMethod(ls);
            map.put(String.valueOf(x), ls);
        }
        return map;
    }

    /**
     * 判断第一个字符是否为中文
     */
    public static boolean FirstCharacterIsChisese(String s) {

        //把传入的display字符串转化为字符数组     "大家好" ——> {"大","家","好"}
        char[] t1 = null;
        t1 = s.toCharArray();

        // 判断首字符是否为汉字
        if (Character.toString(t1[0]).matches("[\\u4E00-\\u9FA5]+")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断一个字符串的首字符是否为字母
     */
    public static boolean FirstCharacterIsEnglish(String s)
    {
        char c = s.charAt(0);
        int i =(int)c;
        if((i>=65&&i<=90)||(i>=97&&i<=122))
        {
            return   true;
        }
        else
        {
            return   false;
        }
    }

    /**
     * 得到中文首字母
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        char word = str.charAt(0);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
        if (pinyinArray != null) {
            convert += pinyinArray[0].charAt(0);
        } else {
            convert += word;
        }
        return convert;
    }

    /**
     * 将字符串转移为ASCII码
     *
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        strBuf.append(Integer.toHexString(bGBK[0] & 0xff));
        return strBuf.toString();
    }


    /**
     * 得到 全拼 , 搜索已添加好友可以用
     */
    public static String getPingYin(String s){
        //把传入的display字符串转化为字符数组     "大家好" ——> {"大","家","好"}
        char[] t1 = null;
        t1 = s.toCharArray();
        //存储转换成拼音后的t1             {"大","家","好"} ——> {"DA","JIA","HAO"}
        String[] t2 = new String[t1.length];

        //拼音输出的格式化 :  “行 ” , “xing2” , “xing” , “xíng”
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //-LOWERCASE：小写 (xing)   -UPPERCASE：大写 (XING)
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //WITHOUT_TONE : 无音标 (xing)
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // WITH_V : 用v表示ü (nv)
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        //存储汉字字符
        String t4 = "";
        //
        String t5 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], format);
                    t4 += t2[0];
                } else {
                    t4 += Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return null;
    }




}
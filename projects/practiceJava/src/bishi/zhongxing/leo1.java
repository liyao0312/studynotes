package bishi.zhongxing;

import java.util.*;

/**
 * @author ：cr
 * @date ：Created in 2019/8/8 16:14
 * @description：$工资排序问题： 给N名员工的工资清单，按照工资分布的人数降序排序，如果有两个工资的人的数目一致（大概意思就是假设3000工
 *  *  * 资有3个人，5000也是三个人），就按照工资在给定清单的顺序里面排序。
 * @modified By：cr
 * @version: $
 */
public class leo1 {
    public static List<Integer> sortSalary(int[] salaries){

        List<Integer> salaryResultList = new ArrayList();
        //声明一个LinkedHashMap
        Map<Integer,Integer> freTimesMap = new LinkedHashMap<Integer, Integer>();
        //声明一个List
        List<Map.Entry<Integer,Integer>> tempofList = null;
        //
        for(int i=0;i<salaries.length;i++){
            boolean hasKey = freTimesMap.containsKey(salaries[i]);
            if(!hasKey){
                freTimesMap.put(salaries[i],1);

            }else {
                freTimesMap.put(salaries[i],freTimesMap.get(salaries[i])+1);
            }
        }

        tempofList = new ArrayList(freTimesMap.entrySet());
        //5000
        Collections.sort(tempofList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        //
        for(Map.Entry<Integer,Integer> entry : tempofList){
            for(int i=0;i<entry.getValue();i++){
                salaryResultList.add(entry.getKey());
            }
        }
        return salaryResultList;
    }

    //打印工资清单
    public static void printSalaryList(List<Integer> salaryList){
        boolean notHead = false;
        Iterator<Integer> iterator = salaryList.iterator();

        while (iterator.hasNext()){
            if(notHead) System.out.print(","+iterator.next());
            else {
                System.out.print(iterator.next());
                notHead = true;
            }
        }
    }
    //测试
    public static void main(String[] args){
        List<Integer> salaryList;
        int[] nums = {10000,20000,40000,30000,30000,30000,40000,20000,50000,50000,50000,50000,60000,60000,60000,70000,80000,90000,100000};
        salaryList = sortSalary(nums);
        printSalaryList(salaryList);
    }
}

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
    该文件，可以深度遍历文件夹，通过修改文件夹路径。
    遍历文件夹后，通过访问文件名，获取到学生学号。
    创建数组，学号对应数组的位置，进行加法。查看学生提交的作业次数。

    注意：(搜索TODO关键字，查看更改点)
        优化方案
        1. 读取txt文件，将学生学号，姓名导入到List里面。  便于输出提示打印。
        2.
 */
public class FoldStuHomeworkCount {

    public static void main(String[] args) {

        // 指定要遍历的文件夹路径   TODO 进行修改文件夹，注意可以深度遍历
        String folderPath = "J:\\信息技术\\1-建筑工程技术2402-周一上"; // 替换为你的文件夹路径

        // 创建 File 对象
        File folder = new File(folderPath);

        // 调用递归方法遍历文件夹
        int[] students = new int[60];
        traverseFolder(folder,students);
        for (int i = 0; i < students.length; i++) {
            System.out.println("学号" + i + ":\t" + students[i]);
//            System.out.println(students[i]);
        }

    }

    /**
     * 递归遍历文件夹
     *
     * @param folder 要遍历的文件夹
     */
    public static void traverseFolder(File folder,int[] students) {
        // 检查文件夹是否存在
        if (!folder.exists()) {
            System.out.println("文件夹不存在: " + folder.getAbsolutePath());
            return ;
        }

//        int[] students = new int[60];

        // 获取文件夹中的所有文件和子文件夹
        File[] files = folder.listFiles();

        // 检查是否为空
        if (files == null || files.length == 0) {
            System.out.println("文件夹为空: " + folder.getAbsolutePath());
            return ;
        }

        // 遍历文件和子文件夹
        for (File file : files) {
            if (file.isDirectory()) {
                // 如果是文件夹，递归调用
//                System.out.println("进入文件夹: " + file.getAbsolutePath());
                traverseFolder(file,students);
            } else {
                // 如果是文件，输出文件路径
//                System.out.println("找到文件: " + file.getAbsolutePath());
                String fileName = file.getName();

                // 输出文件名
//                System.out.println("文件名: " + fileName);


                // 提取汉字的正则表达式
                String chineseRegex = "[\\u4e00-\\u9fa5]+";

                // 提取数字的正则表达式
                String digitRegex = "\\d+";

                // 提取汉字
                Pattern chinesePattern = Pattern.compile(chineseRegex);
                Matcher chineseMatcher = chinesePattern.matcher(fileName);

                while (chineseMatcher.find()) {
                    String chinese = chineseMatcher.group();
//                    System.out.println(chinese);
                }

                // 提取数字
                Pattern digitPattern = Pattern.compile(digitRegex);
                Matcher digitMatcher = digitPattern.matcher(fileName);

//                System.out.println("\n提取的数字:");
                while (digitMatcher.find()) {
                    String digit = digitMatcher.group();
//                    System.out.println(digit);
                    if (digit.length() == 10) {
//                        System.out.println(digit);
                        // 获取字符串的最后两位字符
                        String lastTwoDigits = digit.substring(digit.length() - 2);

                        // 输出结果
//                        System.out.println("最后两位数字: " + lastTwoDigits);
                        students[Integer.parseInt(lastTwoDigits)]++;
                    }
                }


            }
        }


    }
}



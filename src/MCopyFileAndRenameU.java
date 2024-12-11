import java.io.*;
import java.util.ArrayList;


public class MCopyFileAndRenameU {

    //使用： 复制（fileuse文件夹里的文件，根据学生名单复制多份并重命名。）
    /*
            1. 把文件放在FIleUse里面，
            2. 然后把文件名改成自己的文件名。
            3. 修改学生名单名字

     */

    public static void main(String[] args) throws Exception {
        ArrayList<String> arrayList = new ArrayList();
        String[] names = new String[102];
        //读取当前文件夹下的文件

        //TODO 再次修改人员名字所在的目录
        File nameFile = new File("src/Students/test.txt");// Text文件


        BufferedReader br = new BufferedReader(new FileReader(nameFile));// 构造一个BufferedReader类来读取文件
        String s = null;
        while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
            arrayList.add(s);
        }
        br.close();

        for (int jk = 0; jk < arrayList.size(); jk++) {

            //TODO 再次修改需要复制的文件名
            String fileName  ="demo1.docx";
            String filepath ="src\\FileUse\\"+fileName;

//            String filepath = "src\\FileUse\\aa.txt";
            String newFilepath  = "src\\CopySave\\";

            File file = new File(filepath);
            if (!file.isDirectory()) { //如果file不是一个文件夹
                copyFile(filepath,newFilepath);
                renameFile(newFilepath,fileName,arrayList.get(jk)+".docx");
            }
        }
    }


    /**
     * 重命名文件
     *
     * @param path    文件夹路径
     * @param oldName 要重命名的文件名称
     * @param newName 重命名后的文件名称
     */
    public static void renameFile(String path, String oldName, String newName) {
        if (!oldName.equals(newName)) {
            File oldFile = new File(path + "/" + oldName);
            File newFile = new File(path + "/" + newName);
            if (newFile.exists()) {
                System.out.println(newName + "已经存在");
            } else {
                oldFile.renameTo(newFile);
            }
        }


    }

    /**
     * 拷贝文件
     *
     * @param file1 要拷贝的文件路径
     * @param file2 要复制到的文件夹目录
     * @return
     */
    public static boolean copyFile(String file1, String file2) {
        File in = new File(file1);
        File out = new File(file2);
        //要拷贝的文件
        if (!in.exists()) {
            System.out.println(in.getAbsolutePath() + "要拷贝的文件路径错误！！！");
            return false;
        }
        //目标文件夹
        if (!out.exists()) {
            out.mkdirs();
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //创建读取 要拷贝的文件
            fis = new FileInputStream(in);
            //创建 要复制到的文件
            fos = new FileOutputStream(new File(file2 + "\\" + in.getName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int c;
        //创建字节数组
        byte[] b = new byte[1024 * 5];
        try {
            //写入文件
            while ((c = fis.read(b)) != -1) {
                fos.write(b, 0, c);
            }
            fis.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 拷贝文件夹
     *
     * @param file1 要拷贝的文件夹
     * @param file2 拷贝的文件夹
     * @return
     */
    public static boolean copyFolder(String file1, String file2) {
        File in = new File(file1);
        File out = new File(file2);
        //要拷贝的文件夹
        if (!in.exists()) {
            System.out.println(in.getAbsolutePath() + "要拷贝的文件夹路径错误！！！");
            return false;
        }
        //目标文件夹
        if (!out.exists()) {
            out.mkdirs();
        }
        //要拷贝的文件夹下的文件或文件夹
        String[] file = in.list();
//		File[] file = in.listFiles();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File temp = null;
        for (int i = 0; i < file.length; i++) {

            if (file1.endsWith(File.separator)) {
                temp = new File(file1 + file[i]);
            } else {
                temp = new File(file1 + File.separator + file[i]);
            }
            //是文件
            if (temp.isFile()) {
                try {
                    //创建读取 要拷贝的文件
                    fis = new FileInputStream(temp.getAbsolutePath());
                    //创建写入 要拷贝的文件
                    fos = new FileOutputStream(new File(file2 + "\\" + temp.getName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (temp.isDirectory()) {
                //是文件夹
                copyFolder(temp.getAbsolutePath(), file2 + "\\" + temp.getName());
            }
            int c;
            //创建字节数组
            byte[] b = new byte[1024 * 5];
            try {
                //写入文件
                while ((c = fis.read(b)) != -1) {
                    fos.write(b, 0, c);
                }
                fis.close();
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;

    }
}

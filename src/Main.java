import java.io.*;
import java.util.ArrayList;


/*
 功能： 实现复制文件夹并且根据 txt文件里的名字进行重命名。
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<String> arrayList = new ArrayList();
        String[] names = new String[102];
        //读取当前文件夹下的文件

        //TODO 再次修改人员名字所在的目录
        File nameFile = new File("src/aa.txt");// Text文件



        BufferedReader br = new BufferedReader(new FileReader(nameFile));// 构造一个BufferedReader类来读取文件
        String s = null;
        while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
//            System.out.println(s);
            arrayList.add(s);
        }
        br.close();


        for (int jk = 0; jk < arrayList.size(); jk++) {




        // 要读取的文件夹路径
//        String filepath = "C:\\Users\\admin\\Desktop\\logo";

            //TODO 再次修改需要复制的文件夹
        String filepath = "src\\dd";
        String newFilepath  = "src\\" + arrayList.get(jk);
        File file = new File(filepath);
        System.out.println("file.isDirectory() = " + file.isDirectory());
        copyFolder(filepath,newFilepath);
        if (!file.isDirectory()) {
//            System.out.println("文件");
//            System.out.println("path=" + file.getPath());
//            System.out.println("absolutepath=" + file.getAbsolutePath());
//            System.out.println("name=" + file.getName());
        } else if (file.isDirectory()) {
            String[] filelist = file.list();
            System.out.println("文件个数：" + file.list().length);
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "\\" + filelist[i]);
                if (!readfile.isDirectory()) {
//					System.out.println("path=" + readfile.getPath());
//					System.out.println("absolutepath=" + readfile.getAbsolutePath());
//					System.out.println("name=" + readfile.getName());
                    //文件名称
                    String bankName = readfile.getName();
                    //文件名称前缀
                    String name = bankName.substring(0, bankName.lastIndexOf("."));
                    System.out.println("文件名称前缀:" + name);
                    //将文件名称转换为拼音
//                    String nameToSpell = ChineseToSpell.getFullSpell(name);
//                    System.out.println("文件名称前缀转换为拼音名称名称:" + nameToSpell);
                    //要更改的文件名称
//                    String newName = nameToSpell + bankName.substring(bankName.lastIndexOf("."));
//                    String newName = "aaaaaaa";
                    //重命名文件
//                    renameFile(filepath, name, newName);

                }
            }
        }
//		String file1 = "C:\\Users\\admin\\Desktop\\logo - 副本（2）";
//		String file2 = "C:\\Users\\admin\\Desktop\\logo - 副本（3）";
//		System.out.println(copyFolder(file1, file2));

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
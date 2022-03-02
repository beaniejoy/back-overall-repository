package io.beaniejoy.stream;

import java.io.File;

public class FileEx2 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("java FileEx2 DIRECTORY");
            System.exit(-1);
        }

        File f = new File(args[0]);

        if (!f.exists() || !f.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리 입니다.");
            System.exit(-1);
        }

        File[] files = f.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            System.out.println(file.isDirectory() ? "[" + fileName + "]" : fileName);
        }
    }
}

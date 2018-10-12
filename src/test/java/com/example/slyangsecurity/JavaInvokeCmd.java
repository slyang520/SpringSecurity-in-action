package com.example.slyangsecurity;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JavaInvokeCmd {

    @Test
    public void hello() {

        String[] arguments = new String[]{
                "git",
                "status"
        };

        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            int re = process.waitFor();
            System.out.println("进程退出状态码: "+ re);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

package com.company;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws JSONException, IOException {

        String fileName = System.getProperty("user.dir")+"//src//resources//data.json";
        String json = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray arr = jsonObject.getJSONArray("MCQ");

        int score=0;
        int max_ques=5;
        String[] failed=new String[arr.length()];
        String[] correctAnswer=new String[arr.length()];
        for(int count=1;count<=max_ques;count++){
            int i=GenerateRandom.getRandomNumber(0,arr.length()-1);
            String[] q=new String[arr.length()];
            System.out.println("Ques no #"+count);
            q[i]=arr.getJSONObject(i).getString("ques");
            String o1=arr.getJSONObject(i).getString("option1");
            String o2=arr.getJSONObject(i).getString("option2");
            String o3=arr.getJSONObject(i).getString("option3");
            String o4=arr.getJSONObject(i).getString("option4");
            String[] a=new String[arr.length()];
            a[i]=arr.getJSONObject(i).getString("ans");
            Question questions= new Question(q[i],a[i]);
            System.out.println(questions.question);
            System.out.println("a."+o1+"\nb."+o2+"\nc."+o3+"\nd."+o4);
            Scanner input=new Scanner(System.in);
            String answer=input.next();

            if(answer.equals(questions.answer)){
                score++;
            }
            else{
                failed[i]=q[i];
                correctAnswer[i]=a[i];
            }

        }

        if(score!=max_ques){
            System.out.println("You get "+score+" out of "+max_ques);
            System.out.println("You failed to answer following questions:\n");
            for(int i=0;i<arr.length();i++){
                if(failed[i]!=null){
                    System.out.println(failed[i]);
                    System.out.println("Correct answer: "+correctAnswer[i]);
                }

            }

        }
        else{
            System.out.println("Congrats! You get "+score+" out of "+max_ques);
        }


    }
}

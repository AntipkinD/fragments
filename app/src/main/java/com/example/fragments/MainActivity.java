package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragments.MenuFragment;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Random goodrnd = new Random();
    public Random badrnd = new Random();
    public Integer enemiescounter;
    public Integer enemiesnumber = 25;
    public TextView counter;
    public Intent intent;
    public Integer strikenumber = 3;
    public TextView timerView;
    public Integer mlseconds = 10000;
    public String winordefeat = "Поражение";
    public boolean start = false;
    public boolean finish = false;
    protected boolean winstate = false;
    public static Integer money = 0;
    CountDownTimer timer;
    public Fragment gamefragment;
    public Fragment menufragment;
    public Button redbtn, startbtn, restartbtn, nextlvlbtn, upgrade1btn, upgrade2btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setEnemies();
        menufragment = getFragmentManager().findFragmentById(R.id.fragment_menu);
        gamefragment = getFragmentManager().findFragmentById(R.id.fragment_game);
        redbtn = gamefragment.getView().findViewById(R.id.red_button);
        redbtn.setOnClickListener(this::onClick);
        startbtn = gamefragment.getView().findViewById(R.id.start_button);
        startbtn.setOnClickListener(this::onStartTimer);
        restartbtn = menufragment.getView().findViewById(R.id.restart_button);
        restartbtn.setOnClickListener(this::onClickRestart);
        nextlvlbtn = menufragment.getView().findViewById(R.id.next_button);
        nextlvlbtn.setOnClickListener(this::onNextLevel);
        upgrade1btn = menufragment.getView().findViewById(R.id.upgrade_button1);
        upgrade1btn.setOnClickListener(this::onUpgrade1);
        upgrade2btn = menufragment.getView().findViewById(R.id.upgrade_button2);
        upgrade2btn.setOnClickListener(this::onUpgrade2);
        nextlvlbtn.setEnabled(false);
        restartbtn.setEnabled(false);
        upgrade1btn.setEnabled(false);
        upgrade2btn.setEnabled(false);
        timer = new CountDownTimer(this.mlseconds, 1000) {
            @Override
            public void onTick(long miliseconds) {
                ((TextView) gamefragment.getView().findViewById(R.id.winstatetimer)).setText(""+miliseconds/1000);
            }

            @Override
            public void onFinish() {
                finish = true;
                toKill();
            }
        };
    }
    public void onClick(View view){
        toKill();
    }
    public void setEnemies() {
        gamefragment = getFragmentManager().findFragmentById(R.id.fragment_game);
        this.enemiescounter = this.badrnd.nextInt(this.enemiesnumber);
        ((TextView) gamefragment.getView().findViewById(R.id.enemies_counter)).setText(this.enemiescounter.toString());
    }
    public void toKill(){
        if (this.start == true){
            if (this.enemiescounter > 0){
                this.winstate = false;
                if (finish == false)
                    this.enemiescounter=this.enemiescounter-this.goodrnd.nextInt(this.strikenumber);
                ((TextView) gamefragment.getView().findViewById(R.id.enemies_counter)).setText(this.enemiescounter.toString());
                this.winordefeat = "Поражение";
                if (finish == true){
                    ((TextView) gamefragment.getView().findViewById(R.id.winstatetimer)).setText(winordefeat);
                    if (winstate == false){
                        this.money += 0;
                        ((TextView) menufragment.getView().findViewById(R.id.moneycounter)).setText(this.money.toString());
                        redbtn.setEnabled(false);
                        nextlvlbtn.setEnabled(false);
                        restartbtn.setEnabled(true);
                        upgrade1btn.setEnabled(true);
                        upgrade2btn.setEnabled(true);
                    }
                }
            }
            if (this.enemiescounter <= 0){
                this.winstate = true;
                this.finish = true;
                this.enemiescounter = 0;
                ((TextView) gamefragment.getView().findViewById(R.id.enemies_counter)).setText(this.enemiescounter.toString());
                this.timer.cancel();
                this.winordefeat = "Победа!";
                if (finish == true){
                    if (winstate == true){
                        ((TextView) gamefragment.getView().findViewById(R.id.winstatetimer)).setText(winordefeat);
                        if (this.enemiesnumber >= 25 & this.enemiesnumber < 50)
                            this.money += 100;
                        if (this.enemiesnumber >= 50 & this.enemiesnumber < 70)
                            this.money += 200;
                        if (this.enemiesnumber >= 70 & this.enemiesnumber < 100)
                            this.money += 400;
                        if (this.enemiesnumber >= 100)
                            this.money += 600;
                        ((TextView) menufragment.getView().findViewById(R.id.moneycounter)).setText(this.money.toString());
                        redbtn.setEnabled(false);
                        nextlvlbtn.setEnabled(true);
                        restartbtn.setEnabled(false);
                        upgrade1btn.setEnabled(true);
                        upgrade2btn.setEnabled(true);
                    }
                }
            }
        }
    }
    public void onStartTimer(View view){
        this.timer.start();
        startbtn.setEnabled(false);
        redbtn.setEnabled(true);
        this.start = true;
    }
    public void onClickRestart(View view){
        this.strikenumber = this.strikenumber;
        this.mlseconds = this.mlseconds;
        this.money = this.money;
        this.enemiesnumber = this.enemiesnumber;
        badrnd = new Random(this.strikenumber);
        setEnemies();
        startbtn.setEnabled(true);
        nextlvlbtn.setEnabled(false);
        restartbtn.setEnabled(false);
        upgrade1btn.setEnabled(false);
        upgrade2btn.setEnabled(false);
        winstate = false;
        finish = false;
        start = false;
        ((TextView) gamefragment.getView().findViewById(R.id.winstatetimer)).setText("Количество врагов");
        timer = new CountDownTimer(this.mlseconds, 1000) {
            @Override
            public void onTick(long miliseconds) {
                ((TextView) gamefragment.getView().findViewById(R.id.winstatetimer)).setText(""+miliseconds/1000);
            }

            @Override
            public void onFinish() {
                finish = true;
                toKill();
            }
        };
    }
    public void onNextLevel(View view){
        this.strikenumber = this.strikenumber;
        this.mlseconds = this.mlseconds;
        this.money = this.money;
        this.enemiesnumber += this.strikenumber*7;
        badrnd = new Random(this.strikenumber);
        setEnemies();
        startbtn.setEnabled(true);
        nextlvlbtn.setEnabled(false);
        restartbtn.setEnabled(false);
        upgrade1btn.setEnabled(false);
        upgrade2btn.setEnabled(false);
        winstate = false;
        finish = false;
        start = false;
        ((TextView) gamefragment.getView().findViewById(R.id.winstatetimer)).setText("Количество врагов");
        timer = new CountDownTimer(this.mlseconds, 1000) {
            @Override
            public void onTick(long miliseconds) {
                ((TextView) gamefragment.getView().findViewById(R.id.winstatetimer)).setText(""+miliseconds/1000);
            }

            @Override
            public void onFinish() {
                finish = true;
                toKill();
            }
        };
    }
    public void onUpgrade1(View view){
        if (this.money >= 400) {
            this.money -= 400;
            this.strikenumber += 1;
            ((TextView) menufragment.getView().findViewById(R.id.moneycounter)).setText(this.money.toString());
        }
        else Toast.makeText(this, "Вам не хватает денег", Toast.LENGTH_SHORT).show();
    }
    public void onUpgrade2(View view){
        if (this.money >= 1000) {
            this.money -= 1000;
            this.mlseconds += 5000;
            ((TextView) menufragment.getView().findViewById(R.id.moneycounter)).setText(this.money.toString());
        }
        else Toast.makeText(this, "Вам не хватает денег", Toast.LENGTH_SHORT).show();
    }
}
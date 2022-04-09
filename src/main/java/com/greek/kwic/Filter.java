package com.greek.kwic;

import java.io.IOException;


public abstract class Filter implements Runnable {
    protected Pipe input;
    protected Pipe output;
    private boolean is_started = false;
    public Filter(Pipe input, Pipe output) {
        this.input = input;
        this.output = output;
    }
    public void start() {
        // 防止多次调用
        if(!is_started) {
            is_started = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        try {
            transform();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected abstract void transform() throws IOException;
}

package com.vk18.splitwise.Command;

public interface Command {
    public boolean IsMatching(String command);

    public void execute(String command);
}

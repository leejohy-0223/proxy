package hello.proxy.filterTest.controller;

import lombok.ToString;

@ToString
public class PostDto {

    private String name;
    private String company;

    public PostDto(String name, String company) {
        this.name = name;
        this.company = company;
    }
}

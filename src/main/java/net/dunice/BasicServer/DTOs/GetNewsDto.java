package net.dunice.BasicServer.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter

public class GetNewsDto<T> {

    private List<T> content;

    private long numberOfElements;

    private long ready;

    private long notReady;
}
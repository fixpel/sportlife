package com.example.sportlife.AndroidBackGround.Dto.Response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindInventoryResponse {
    private List<InventoryObject> inventories;
    private int size;
    private int page;
    private int totalPage;

    @NoArgsConstructor
    @Data
    @AllArgsConstructor
    public static class InventoryObject {
        private String name;
        private String photo;
        }
}

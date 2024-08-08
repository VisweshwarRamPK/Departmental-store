package com.viswa.DepartmentalStoreApplication.model;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMIN(Arrays.asList(



            Permissions.GET_LIST


    )),
    CUSTOMER(Arrays.asList(
            Permissions.ADDCARTPRODUCTS


    )),SUPERADMIN(Arrays.asList( Permissions.GRANT_ADMIN,


                 Permissions.GET_LIST)

    );
    private List<Permissions> permissions;
    public List<Permissions> getPermissions(){
        return permissions;
    }
    public void setPermissions(List<Permissions> permissions){
        this.permissions = permissions;
    }
    Role(List<Permissions> permissions){
        this.permissions = permissions;
    }
}
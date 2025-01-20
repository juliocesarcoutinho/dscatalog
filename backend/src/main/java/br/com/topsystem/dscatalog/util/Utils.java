package br.com.topsystem.dscatalog.util;

import br.com.topsystem.dscatalog.entities.Product;
import br.com.topsystem.dscatalog.projections.IdProjection;
import br.com.topsystem.dscatalog.projections.ProductProjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils { // mapear a lista de produtos ordenada com a lista de produtos não ordenada
    public static <ID> List<? extends IdProjection<ID>> replace(List<? extends IdProjection<ID>> ordered,
                                                                List<? extends IdProjection<ID>> unordered) {

        Map<ID, IdProjection<ID>> map = new HashMap<>();
        for (IdProjection<ID> obj : unordered) {
            map.put(obj.getId(), obj);
        }

        List<IdProjection<ID>> result = new ArrayList<>();
        for (IdProjection<ID> obj : ordered) {
            result.add(map.get(obj.getId()));
        }


        return result;
    }
}

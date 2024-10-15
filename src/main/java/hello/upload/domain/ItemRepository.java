package hello.upload.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {

    // final 일종의 상수 취급 변경 x
    private final Map<Long, Item> store = new HashMap<>();
    // Long은 참조, long은 기본 타입, 비교해서 Long은 null을 가질 수 있다(long은 불가)
    private long sequence = 0L;

    public Item save(Item item) {
        item.setId(sequence++);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(long id) {
        return store.get(id);
    }
}

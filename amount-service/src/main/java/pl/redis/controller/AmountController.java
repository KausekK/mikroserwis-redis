package pl.redis.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import pl.redis.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/amount")
public class AmountController {

    private static final String COUNTER_KEY = "amount:sum";
    private static final String HISTORY_KEY = "amount:history";

    @Value("${INSTANCE_NAME}")
    private String instanceName;

    private final StringRedisTemplate redis;

    public AmountController(StringRedisTemplate redis) {
        this.redis = redis;
    }

    @PostMapping("/add")
    public ResponseDto addAmount() {
        Long newValue = redis.opsForValue().increment(COUNTER_KEY, 1);
        redis.opsForList().rightPush(HISTORY_KEY, String.valueOf(newValue));
        return new ResponseDto(newValue, instanceName);
    }

    @GetMapping
    public Long getAmount() {
        String value = redis.opsForValue().get(COUNTER_KEY);
        return value == null ? 0L : Long.parseLong(value);
    }

    @DeleteMapping
    public Long resetAmount() {
        redis.delete(COUNTER_KEY);
        return 0L;
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        return redis.opsForList().range(HISTORY_KEY, 0, -1);
    }
}

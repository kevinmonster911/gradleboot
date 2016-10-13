package hello.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController {

    protected ApiResult errorResult(Integer code,
                                    String rootMessage) {

        return errorResult(code, rootMessage, null, null, null);
    }

    protected ApiResult errorResult(Integer code,
                                   String rootMessage,
                                   String domain,
                                   String reason,
                                   String message) {

        return  ApiResult.ApiResultBuilder.newBuilder()
                .error(code, rootMessage)
                .errorEntry(domain, reason, message)
                .build();

    }

    protected ApiResult result(Object data) {
        return ApiResult.ApiResultBuilder.newBuilder()
                .data(data)
                .build();
    }

    protected ApiResult results(Object data) {
        return results(Arrays.asList(data));
    }

    protected ApiResult results(List<?> datas) {

        return ApiResult.ApiResultBuilder.newBuilder()
                .datas()
                .dataEntries(datas)
                .build();
    }

    protected Map<String, Object> generateMap(String k1, Object v1){
        Map<String, Object> map = new HashMap<>();
        map.put(k1, v1);
        return map;
    }
}

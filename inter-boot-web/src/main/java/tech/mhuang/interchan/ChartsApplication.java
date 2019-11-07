package tech.mhuang.interchan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/charts")
public class ChartsApplication {

    public class Result<T> {
        private int code; //状态码
        private String message;//消息
        private T data;

        public Result() {

        }

        public Result(int code, String message, T data) {
            super();
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Result<?> ok(Object data) {
            return new Result<>(200, "ok", data);
        }
    }

    @ResponseBody
    @RequestMapping("/testPie")
    public Result testPie(@RequestParam(required = false) String name) {
        Map<String, Integer> a = new HashMap<>();

        if (null == name || "".equals(name)) {
            a.put("a", 2);
            a.put("b", 3);
            a.put("c", 4);
        } else {
            a.put("a", 1);
            a.put("b", 2);
            a.put("c", 3);
        }
        return new Result().ok(a);
    }

}

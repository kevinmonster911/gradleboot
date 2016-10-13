package hello.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinmonster on 16/6/10.
 */
public class ApiResult {
    // TODO version api need to extract to config
    private static final String API_VERISON = "1.0-SNAPSHOT";
    private String apiVerison = API_VERISON;

    private ApiError error;

    private Object data;

    public static class ApiResultBuilder {

        private ApiResult apiResult = null;

        public static ApiResultBuilder newBuilder(){

            ApiResultBuilder apiResultBuilder = new ApiResultBuilder();
            apiResultBuilder.apiResult = new ApiResult();
            return apiResultBuilder;
        }

        public ApiResultBuilder error(Integer code, String message){

            if(this.apiResult.error == null) this.apiResult.error = new ApiError();

            this.apiResult.error.code = code;
            this.apiResult.error.message = message;

            return this;
        }

        public ApiResultBuilder errorEntry(String domain, String reason, String message){

            if(this.apiResult.error == null) this.apiResult.error = new ApiError();

            this.apiResult.error.addErrorEntry(domain, reason, message);

            return this;
        }

        public ApiResultBuilder data(Object data){
            if(this.apiResult.data == null) this.apiResult.data = data;
            return this;
        }


        public ApiResultBuilder datas(){
            // TODO need optimize for flexible;
           return datas(20, 10, 0);

        }

        public ApiResultBuilder datas(Integer itemsPerPage,
                                      Integer itemCount,
                                      Integer page){

            if(this.apiResult.data == null) this.apiResult.data = new ApiData();
            ApiData apiData = (ApiData)this.apiResult.data;
            if(apiData.items == null) apiData.items = new ArrayList<>();

            apiData.itemsPerPage = itemsPerPage;
            apiData.itemCount = itemCount;
            apiData.page = page;

            return this;

        }

        public ApiResultBuilder dataEntries(List<?> entries){

            if(this.apiResult.data == null) this.apiResult.data = new ApiData();

            for(Object entry : entries){
                dataEntry(entry);
            }

            return this;
        }

        public ApiResultBuilder dataEntry(Object entry){

            if(this.apiResult.data == null) this.apiResult.data = new ApiData();
            ApiData apiData = (ApiData)this.apiResult.data;

            apiData.addDataEntry(entry);

            return this;
        }

        public ApiResult build(){
            return this.apiResult;
        }

    }

    public static class ApiData {

        private Integer itemsPerPage;
        private Integer itemCount;
        private Integer page;
        private Object item;
        private List<Object> items;

        public Integer getItemsPerPage() {
            return itemsPerPage;
        }

        public void setItemsPerPage(Integer itemsPerPage) {
            this.itemsPerPage = itemsPerPage;
        }

        public Integer getItemCount() {
            return itemCount;
        }

        public void setItemCount(Integer itemCount) {
            this.itemCount = itemCount;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public List<Object> getItems() {
            return items;
        }

        public void setItems(List<Object> items) {
            this.items = items;
        }

        public void addDataEntry(Object data){
            this.items.add(data);
        }

        public Object getItem() {
            return item;
        }

        public void setItem(Object item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return "ApiData{" +
                    "itemsPerPage=" + itemsPerPage +
                    ", itemCount=" + itemCount +
                    ", page=" + page +
                    ", items=" + items +
                    '}';
        }
    }


    public static class ApiError {

        private Integer code;
        private String message;
        private List<ApiErrorEntry> errors = new ArrayList<>();

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ApiErrorEntry> getErrors() {
            return errors;
        }

        public void setErrors(List<ApiErrorEntry> errors) {
            this.errors = errors;
        }

        public void addErrorEntry(String domain, String reason, String message){
            ApiErrorEntry apiErrorEntry = new ApiErrorEntry();
            apiErrorEntry.setDomain(domain);
            apiErrorEntry.setMessage(message);
            apiErrorEntry.setReason(reason);
            this.errors.add(apiErrorEntry);
        }

        @Override
        public String toString() {
            return "ApiError{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    ", errors=" + errors +
                    '}';
        }
    }

    public static class ApiErrorEntry {
        private String domain;
        private String reason;
        private String message;

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ApiErrorEntry{" +
                    "domain='" + domain + '\'' +
                    ", reason='" + reason + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    public String getApiVerison() {
        return apiVerison;
    }

    public void setApiVerison(String apiVerison) {
        this.apiVerison = apiVerison;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "apiVerison='" + apiVerison + '\'' +
                ", error=" + error +
                ", datas=" + data +
                '}';
    }
}

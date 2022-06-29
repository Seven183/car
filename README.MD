此项目下的前端需要所有的controller 映射上加上 /prod-api才能访问 因为服务器上使用nginx 转发的
例如：
    @RestController
    @RequestMapping("/prod-api/advice")
    public class AdvicesController {
    
        @Autowired
        private AdvicesService advicesService;
    
        @GetMapping(value = "/allAdvices")
        public Result<PageData<Advices>> allAdvices(AdvicesParameter advicesParameter) {
            PageData<Advices> advicesPageData = advicesService.allAdvices(advicesParameter);
            return Result.ok(advicesPageData);
        }
    }
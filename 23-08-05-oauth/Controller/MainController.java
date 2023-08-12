package com.example.project.Controller;

import com.example.project.Category.CategoryService;
import com.example.project.Category.MainPageService;
import com.example.project.Heart.HeartService;
import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.example.project.Search.SearchService;
import com.example.project.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final CategoryService categoryService;
    private final MainPageService mainPageService;
    private final SearchService searchService;
    private final HeartService heartService;

    /** 메인 **/
    @RequestMapping(value = {"","/logo"})
    public ArrayList main(HttpServletRequest request){
        logger.info("메인 페이지를 로딩합니다···");

        ArrayList datas = new ArrayList();
        String requestURL = request.getRequestURL().toString();
        System.out.println("requestURL = " + requestURL);

        LinkedHashMap<String, Product> page = mainPageService.getPage();

        page.forEach((k, v) -> {
            datas.add(v);
        });
        return datas;
    }

    /** 카테고리 **/
    @GetMapping("/{marketName}/{categoryName}/{pageNum}")
    public ArrayList getCategory(@PathVariable String marketName, @PathVariable int categoryName, @PathVariable int pageNum, HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();

        if (marketName.equals("J")) {
            logger.info("중고나라 카테고리 로딩... requestURL = " + requestURL);
            LinkedHashMap<String, Product> page = categoryService.getPage(Market.JOONGGONARA, categoryName, pageNum);

            ArrayList datas = new ArrayList();
            page.forEach((k, v) -> {
                datas.add(v);
            });
            return datas;
        } else if (marketName.equals("B")) {
            logger.info("번개장터 카테고리 로딩... requestURL = " + requestURL);
            LinkedHashMap<String, Product> page = categoryService.getPage(Market.BUNJANG, categoryName, pageNum);

            ArrayList datas = new ArrayList();
            page.forEach((k, v) -> {
                datas.add(v);
            });
            return datas;
        }

        logger.info("Error: 마켓 이름을 제대로 입력해 주세요! requestURL = " + requestURL);
        return null;
    }

    /** 상품 검색 **/
    @GetMapping("/search/{productName}/{pageNum}")
    public ArrayList getProductSearch(@PathVariable String productName, @PathVariable int pageNum, HttpServletRequest request){
        logger.info("상품명으로 검색을 진행합니다···");
        String requestURL = request.getRequestURL().toString();
        System.out.println("requestURL = " +requestURL +" 상품명 : "+productName);

        if(pageNum==0){ pageNum=1; }

        if(productName!=null){
            LinkedHashMap<String, Product> page1 = searchService.getSearchResult(Market.JOONGGONARA, pageNum, productName);
            LinkedHashMap<String, Product> page2 = searchService.getSearchResult(Market.BUNJANG, pageNum, productName);

            ArrayList datas = new ArrayList();
            page1.forEach((k, v) -> {
                datas.add(v);
            });
            page2.forEach((k, v) -> {
                datas.add(v);
            });
            return datas;
        }
        return null;
    }

    /** 찜 목록 이동 **/
    @GetMapping("/list")
    public ArrayList getHeartList(PrincipalDetails principalDetails, HttpServletRequest request){
        logger.info("찜목록으로 이동합니다···");
        String requestURL = request.getRequestURL().toString();
        System.out.println("requestURL = " + requestURL);

//        User user = principalDetails.getUser();
//        Heart page = user.getHeart();

        ArrayList datas = new ArrayList();
//        page.forEach((k, v) -> {
//            datas.add(v);
//        });
        return datas;
    }
}

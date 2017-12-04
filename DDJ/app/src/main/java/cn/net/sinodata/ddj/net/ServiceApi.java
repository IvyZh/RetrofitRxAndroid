package cn.net.sinodata.ddj.net;


import cn.net.sinodata.ddj.domain.douban.Book;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ivy on 2017/10/27.
 */


public interface ServiceApi {

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
    //                                                                            //
    //                                                                            //
    //                             以下是Book请求的示例                           //
    //                                                                            //
    //                                                                            //
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//


    //获取图书信息
    @GET("/v2/book/{id}")
    Call<Book> getBookInfo(@Path("id") int id);

    //根据isbn获取图书信息
    @GET("/v2/book/isbn/{name}")
    Call<Book> getBookInfo(@Path("name") String id);

    //搜索图书
    @GET("/v2/book/search")
    Call<String> getBookInfo(@Query("q") String q, @Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    // ....

    @GET("/v2/book/{id}")
    Call<String> getBookInfoString(@Path("id") int id);


    @GET("/v2/book/{id}")
    Observable<Book> getBookInfoRxJava(@Path("id") int id);

}

package com.laojiang.androidlearn70.activity.intent;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.laojiang.androidlearn70.R;
import com.laojiang.androidlearn70.activity.BaseActivity;
import com.laojiang.androidlearn70.bean.litepal.Book;
import com.laojiang.androidlearn70.system.L;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类介绍（必填）：litepal数据库demo
 * Created by Jiang on 2017/2/13 10:54.
 */

public class LitePalActivity extends BaseActivity {
    private static final String TAG = "litepal数据库";
    @BindView(R.id.bt_create_db)
    Button btCreateDb;
    @BindView(R.id.bt_add_db)
    Button btAddDb;
    @BindView(R.id.bt_up_db)
    Button btUpDb;
    @BindView(R.id.bt_update_db)
    Button btUpdateDb;
    @BindView(R.id.bt_delete_db)
    Button btDeleteDb;
    @BindView(R.id.bt_query_db)
    Button btQueryDb;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @Override
    protected void initSetContentView() {
        super.initSetContentView();
        setContentView(R.layout.activity_litepal);
    }
    @Override
    protected void initViews() {
        super.initViews();
    }
    private void addData() {
        Book book = new Book();
        book.setAuthor("Lao Jiang");
        book.setName("会飞的虫");
        book.setPages(454);
        book.setPrice(16.56);
        book.save();
    }


    @OnClick({R.id.bt_create_db, R.id.bt_up_db, R.id.bt_add_db, R.id.bt_update_db, R.id.bt_delete_db, R.id.bt_query_db})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_create_db:
                Connector.getDatabase();
                break;
            case R.id.bt_up_db:
                break;
            case R.id.bt_add_db:
                addData();
                break;
            case R.id.bt_update_db:
                updataDB();
                break;
            case R.id.bt_delete_db:
                deleteDB();
                break;
            case R.id.bt_query_db:
                findAll();
                findSelect();
                findWhere();
                findOrder();
                findLimit();
                findOffSet();
                findMore();
                break;
        }
    }
    private void deleteDB() {
        List<Book> allBooks = DataSupport.findAll(Book.class);
        for (Book books:allBooks){
            int i = books.deleteAll(Book.class, "pages<?", "410");
        }
    }

    /**
     * 复杂查询
     */
    private void findMore() {
        List<Book> bookList = DataSupport.select("name", "price", "author")
                .where("pages>?", "200")
                .order("price")
                .limit(3)
                .offset(2)
                .find(Book.class);
        L.i(TAG, "复杂查询-===" + bookList.toString());

    }

    /**
     * 查询结果的偏移量
     * 指定查询表中的第2条，第3条、第4条
     */
    private void findOffSet() {
        List<Book> bookList = DataSupport.limit(3).offset(1).find(Book.class);//offset(1)偏移一个数据
        L.i(TAG, "偏移数据-==" + bookList.toString());


    }

    /**
     * 指定查询结果的数量
     */
    private void findLimit() {
        List<Book> bookList = DataSupport.limit(1).find(Book.class);
    }

    /**
     * 指定排序
     */
    private void findOrder() {
        List<Book> bookList = DataSupport.order("price desc").find(Book.class);
    }

    /**
     * 指定查询的约束条件
     */
    private void findWhere() {
        List<Book> bookList = DataSupport.where("pages>?", "200").find(Book.class);
        L.i(TAG, "指定条件查询-==" + bookList.toString());
    }

    /**
     * 查询指定列
     * 注：只有所查的列有数据，其余的显示默认值
     */
    private void findSelect() {
        List<Book> bookList = DataSupport.select("name", "author").find(Book.class);
        L.i(TAG, "查询指定列的数据-==" + bookList.toString());
    }

    /**
     * 查询所有数据
     */
    private void findAll() {
        List<Book> bookList = DataSupport.findAll(Book.class);
        L.i(TAG, "数据库查询到的所有数据-==" + bookList.toString());
        tvResult.setText(bookList.toString());
    }

    /**
     * 更新数据库
     */
    private void updataDB() {
        Book book = new Book();
        book.setPrice(12.22);
        book.setPages(400);
        book.updateAll("name=? and author = ?", "会飞的虫", "Lao Jiang");

    }


}

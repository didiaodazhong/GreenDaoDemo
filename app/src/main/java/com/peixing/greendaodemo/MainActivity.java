package com.peixing.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.peixing.greendaodemo.bean.User;
import com.peixing.greendaodemo.gen.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private Button btAdd;
    private RecyclerView recyclerview;

    private EditText etNewName;
    private Button btDelete;
    private Button btUpdate;
    private Button btSearch;

    UserAdapter userAdapter;
    List<User> mUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.et_name);
        btAdd = (Button) findViewById(R.id.bt_add);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);


        etNewName = (EditText) findViewById(R.id.et_new_name);
        btDelete = (Button) findViewById(R.id.bt_delete);
        btUpdate = (Button) findViewById(R.id.bt_update);
        btSearch = (Button) findViewById(R.id.bt_search);


        recyclerview.setHasFixedSize(true);
        //设置LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);
//        insertUser(null, "柳七");

        initData();

//        updateUser()
//        deleteUser();
//        insertUser();

      /*  btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertUser(null, etName.getText().toString());
            }
        });*/

        btDelete.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        btAdd.setOnClickListener(this);

        userAdapter.setOnItemClickListener(new UserAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), mUserList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void insertUser(Long id, String name) {
        User findUser = GreenDaoManager.getInstance().getmSession().getUserDao().queryBuilder()
                .where(UserDao.Properties.Name.eq(name)).build().unique();
        if (findUser == null) {
            UserDao userDao = GreenDaoManager.getInstance().getmSession().getUserDao();
            User user = new User(id, name);
            userDao.insert(user);
//        etName.setText("");
            mUserList.clear();
            mUserList.addAll(userDao.queryBuilder().build().list());
            userAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "该用户已存在，无法插入！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 删除用户
     *
     * @param name
     */
    private void deleteUser(String name) {
        UserDao userDao = GreenDaoManager.getInstance().getmSession().getUserDao();
        User findUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
        if (findUser != null) {
            userDao.deleteByKey(findUser.getId());
            mUserList.clear();
            mUserList.addAll(userDao.queryBuilder().build().list());
            userAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "查无此人，无需删除！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 修改该用户信息
     *
     * @param preName
     * @param NewName
     */
    private void updateUser(String preName, String NewName) {
        User findUser = GreenDaoManager.getInstance().getmSession().getUserDao().queryBuilder()
                .where(UserDao.Properties.Name.eq(preName)).build().unique();

        if (findUser != null) {
            findUser.setName(NewName);
            GreenDaoManager.getInstance().getmSession().getUserDao().update(findUser);
            Toast.makeText(getApplicationContext(), "修改成功！", Toast.LENGTH_LONG).show();
            mUserList.clear();
            mUserList.addAll(GreenDaoManager.getInstance().getmSession().getUserDao().queryBuilder().build().list());
            userAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "用户不存在,无法修改！", Toast.LENGTH_LONG).show();
        }
    }

    private void initData() {
        mUserList = GreenDaoManager.getInstance().getmSession().getUserDao().queryBuilder().build().list();

        userAdapter = new UserAdapter(mUserList);
//        if (mUserList.size() > 0) {
        recyclerview.setAdapter(userAdapter);
//        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                if (!TextUtils.isEmpty(etName.getText().toString())) {
                    insertUser(null, etName.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "数据为空！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_delete:
                if (!TextUtils.isEmpty(etName.getText().toString())) {
                    deleteUser(etName.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "数据为空！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_update:
                if (!TextUtils.isEmpty(etName.getText().toString()) && !TextUtils.isEmpty(etNewName.getText().toString())) {
//                    User findUser = GreenDaoManager.getInstance().getmSession().getUserDao().queryBuilder()
//                            .where(UserDao.Properties.Name.eq(etName.getText().toString())).build().unique();
//                    if (findUser != null) {
                    updateUser(etName.getText().toString(), etNewName.getText().toString());
//                    } else {
//                        Toast.makeText(getApplicationContext(), "数据为空！", Toast.LENGTH_SHORT).show();
//                    }
                }
                break;
            case R.id.bt_search:
                initData();
                break;

        }
    }
}

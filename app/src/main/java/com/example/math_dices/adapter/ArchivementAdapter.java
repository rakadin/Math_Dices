package com.example.math_dices.adapter;

//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.math_dices.R;
import com.example.math_dices.model.TotalArchivement;

import java.util.ArrayList;

public class ArchivementAdapter extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    public int id ;
    final ArrayList<TotalArchivement> listProduct;

    public ArchivementAdapter(ArrayList<TotalArchivement> listProduct) {
        this.listProduct = listProduct;
    }
    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listProduct.get(position).ID;
    }
    // set list view
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.listview_archivement, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        TotalArchivement total = (TotalArchivement) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.stt)).setText( Integer.toString(total.getStt()));
        ((TextView) viewProduct.findViewById(R.id.lvname)).setText( total.name);
        int temID = total.getID();
        String comment =total.cmt;
        if(comment.equals(""))
        {
            comment = "Chưa có bình luận";
        }
        ((TextView) viewProduct.findViewById(R.id.lvcmt)).setText( comment);
        ((TextView) viewProduct.findViewById(R.id.lvtrophy)).setText(Integer.toString(total.trophy));
        if(temID == id) // nếu người dùng hiện tại trùng thì làm nổi bật
        {
            viewProduct.setBackgroundColor(R.drawable.gradient_background);
        }
        else if(temID != id) // không thì xóa background
        {
            viewProduct.setBackgroundColor(0);
        }
        return viewProduct;
    }
}

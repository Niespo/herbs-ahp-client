package pl.androidland.studia.herbs.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import pl.androidland.studia.herbs.app.R;
import pl.androidland.studia.herbs.app.api.model.HerbsEntity;
import pl.androidland.studia.herbs.app.api.model.ShopDetail;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HerbsEntityAdapter extends ArrayAdapter<HerbsEntity> {

    private final Context context;

    private final List<HerbsEntity> herbsEntities;

    public HerbsEntityAdapter(Context context, List<HerbsEntity> herbsEntities) {
        super(context, R.layout.herbs_item, herbsEntities);
        this.context = context;
        this.herbsEntities = herbsEntities;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.herbs_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.herbsName = (TextView) convertView.findViewById(R.id.herb_name);
            viewHolder.herbsPrice = (TextView) convertView.findViewById(R.id.herb_price);
            viewHolder.herbsDescription = (TextView) convertView.findViewById(R.id.herb_description);
            viewHolder.herbsShops = (TextView) convertView.findViewById(R.id.herb_shops);
            viewHolder.herbsAccuracy = (TextView) convertView.findViewById(R.id.herb_accuracy);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        final HerbsEntity herbsEntity = this.herbsEntities.get(position);

        if (herbsEntity != null)
            setViewHolder(viewHolder, herbsEntity);

        return convertView;
    }

    private void setViewHolder(ViewHolder viewHolder, HerbsEntity herbsEntity) {

        viewHolder.herbsName.setText(herbsEntity.getHerbsDetail().getName());
        viewHolder.herbsPrice.setText(herbsEntity.getHerbsDetail().getPrice());
        viewHolder.herbsAccuracy.setText(String.valueOf(herbsEntity.getAccuracy()));
        viewHolder.herbsShops.setText(createShopsList(herbsEntity));
        viewHolder.herbsDescription.setText(herbsEntity.getHerbsDetail().getDescription());
    }

    private String createShopsList(HerbsEntity herbsEntity) {
        StringBuffer buffer = new StringBuffer();
        if (herbsEntity.getShops().isEmpty())
            buffer.append(context.getString(R.string.SHOPS_NOT_FOUND));
        else
            createShopDescription(herbsEntity, buffer);

        return buffer.toString();
    }

    private void createShopDescription(HerbsEntity herbsEntity, StringBuffer buffer) {
        for (ShopDetail shopDetail : herbsEntity.getShops())
            buffer.append(shopDetail.getName()).append(", ").append(shopDetail.getAddress()).append(", ")
                    .append(shopDetail.getCity()).append("; ");
    }

    private static class ViewHolder {
        private TextView herbsName;
        private TextView herbsPrice;
        private TextView herbsAccuracy;
        private TextView herbsShops;
        private TextView herbsDescription;
    }

    @Override
    public void notifyDataSetChanged() {
        Collections.sort(herbsEntities, new Comparator<HerbsEntity>() {
            @Override
            public int compare(HerbsEntity a, HerbsEntity b) {
                if (a.getAccuracy() < b.getAccuracy())
                    return -1;
                if (a.getAccuracy() < b.getAccuracy())
                    return 1;
                return 0;
            }
        });
        super.notifyDataSetChanged();
    }
}

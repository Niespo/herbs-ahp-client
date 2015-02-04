package pl.androidland.studia.herbs.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import pl.androidland.studia.herbs.app.R;
import pl.androidland.studia.herbs.app.api.model.response.herb.HerbEntity;
import pl.androidland.studia.herbs.app.api.model.response.herb.ShopDetail;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HerbsEntityAdapter extends ArrayAdapter<HerbEntity> {

    private final Context context;

    private final List<HerbEntity> herbsEntities;

    public HerbsEntityAdapter(Context context, List<HerbEntity> herbsEntities) {
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

        final HerbEntity herbEntity = this.herbsEntities.get(position);

        if (herbEntity != null)
            setViewHolder(viewHolder, herbEntity);

        return convertView;
    }

    private void setViewHolder(ViewHolder viewHolder, HerbEntity herbEntity) {

        viewHolder.herbsName.setText(herbEntity.getHerbDetail().getName());
        viewHolder.herbsPrice.setText(herbEntity.getHerbDetail().getPrice());
        viewHolder.herbsAccuracy.setText(String.valueOf(herbEntity.getAccuracy()));
        viewHolder.herbsShops.setText(createShopsList(herbEntity));
        viewHolder.herbsDescription.setText(herbEntity.getHerbDetail().getDescription());
    }

    private String createShopsList(HerbEntity herbEntity) {
        StringBuffer buffer = new StringBuffer();
        if (herbEntity.getShops().isEmpty())
            buffer.append(context.getString(R.string.SHOPS_NOT_FOUND));
        else
            createShopDescription(herbEntity, buffer);

        return buffer.toString();
    }

    private void createShopDescription(HerbEntity herbEntity, StringBuffer buffer) {
        for (ShopDetail shopDetail : herbEntity.getShops())
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
        Collections.sort(herbsEntities, new Comparator<HerbEntity>() {
            @Override
            public int compare(HerbEntity a, HerbEntity b) {
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

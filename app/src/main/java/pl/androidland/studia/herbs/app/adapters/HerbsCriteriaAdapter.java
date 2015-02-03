package pl.androidland.studia.herbs.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import com.google.common.collect.Maps;
import pl.androidland.studia.herbs.app.R;
import pl.androidland.studia.herbs.app.api.model.HerbsCriterion;

import java.util.List;
import java.util.Map;


public class HerbsCriteriaAdapter extends ArrayAdapter<HerbsCriterion> {

    private final Context context;

    public Map<String, Integer> getPreferencesMap() {
        Map<String, Integer> preferencesMap = Maps.newHashMap();
        int priority;
        for (HerbsCriterion herbsCriterion : herbsCriteria) {
            priority = determinePriority(herbsCriterion);
            preferencesMap.put(herbsCriterion.getEffect(), priority);
        }
        return preferencesMap;
    }

    private int determinePriority(HerbsCriterion herbsCriterion) {
        int priority;
        if (herbsCriterion.isChecked())
            priority = 9;
        else
            priority = 1;
        return priority;
    }

    private final List<HerbsCriterion> herbsCriteria;

    public HerbsCriteriaAdapter(Context context, List<HerbsCriterion> herbsCriteria) {
        super(context, R.layout.herbs_criteria_item, herbsCriteria);
        this.context = context;
        this.herbsCriteria = herbsCriteria;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.herbs_criteria_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.cbHerbsCriteria = (CheckBox) convertView.findViewById(R.id.herbs_criterium);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        final HerbsCriterion herbsCriterion = this.herbsCriteria.get(position);

        if (herbsCriterion != null)
            setViewHolder(viewHolder, herbsCriterion);

        return convertView;
    }

    private void setViewHolder(ViewHolder viewHolder, final HerbsCriterion herbsCriterion) {

        viewHolder.cbHerbsCriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (herbsCriterion.isChecked())
                    herbsCriterion.setChecked(false);
                else
                    herbsCriterion.setChecked(true);
            }
        });

        viewHolder.cbHerbsCriteria.setText(herbsCriterion.getName());
        viewHolder.cbHerbsCriteria.setChecked(herbsCriterion.isChecked());
    }

    private static class ViewHolder {
        private CheckBox cbHerbsCriteria;
    }
}

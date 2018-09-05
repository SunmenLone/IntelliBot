package com.siat.hmc.intellibot.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.entity.LanguageItem;
import com.siat.hmc.intellibot.fragment.LanguageFragment;

import java.util.ArrayList;

public class LanguagePagerAdapter extends FragmentPagerAdapter {

    private final String d1 = "A hamburger, beefburger or burger is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun. The patty may be pan fried, grilled, or flame broiled. Hamburgers are often served with cheese, lettuce, tomato, bacon, onion, pickles, or chiles; condiments such as mustard, mayonnaise, ketchup, relish, or \"special sauce\"; and are frequently placed on sesame seed buns. A hamburger topped with cheese is called a cheeseburger.";
    private final String d2 = "French fries, or just fries (North American English); chips (British and Commonwealth English),[1] finger chips (Indian English),[2] or French-fried potatoes are batonnet or allumette-cut deep-fried potatoes. In the United States and most of Canada, the term fries refers to all dishes of fried elongated pieces of potatoes, while in the United Kingdom, Australia, South Africa (rarely), Ireland and New Zealand, thinly cut fried potatoes are sometimes called shoestring fries or skinny fries to distinguish them from chips, which are cut thicker.";
    private final String d3 = "Cola is a sweetened, carbonated soft drink flavored with vanilla, cinnamon, citrus oils and other flavorings. Most contain caffeine, which was originally sourced from the kola nut, leading to the drink's name, though other sources are now also used. Cola became popular worldwide after pharmacist John Pemberton invented Coca-Cola in 1886.[1] His non-alcoholic recipe was inspired by the coca wine of pharmacist Angelo Mariani, created in 1863.[1]";



    private ArrayList<LanguageItem> list = null;

    public LanguagePagerAdapter(FragmentManager fm) {
        super(fm);

        if (list == null) {
            list = new ArrayList<>();
            list.add(new LanguageItem(R.drawable.hamburger, "Hamburger", d1));
            list.add(new LanguageItem(R.drawable.chips, "Chips", d2));
            list.add(new LanguageItem(R.drawable.cola, "Cola", d3));
        }

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a ReadingFragment (defined as a static inner class below).

        Bundle bundle = new Bundle();
        bundle.putParcelable("data", list.get(position));

        return LanguageFragment.newInstance(position + 1, bundle);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }
}

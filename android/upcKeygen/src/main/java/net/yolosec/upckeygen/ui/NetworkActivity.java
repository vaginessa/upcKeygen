/*
 * Copyright 2012 Rui Araújo, Luís Fonseca
 *
 * This file is part of Router Keygen.
 *
 * Router Keygen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Router Keygen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Router Keygen.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.yolosec.upckeygen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import net.yolosec.upckeygen.R;
import net.yolosec.upckeygen.algorithms.WiFiNetwork;

public class NetworkActivity extends AppCompatActivity {
    private static final String TAG = "NetworkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(Exception e){
            Log.e(TAG, "Exception", e);
        }

        if (savedInstanceState == null) {
            final Bundle arguments = new Bundle();
            final WiFiNetwork wiFiNetwork = getIntent().getParcelableExtra(NetworkFragment.NETWORK_ID);
            arguments.putParcelable(NetworkFragment.NETWORK_ID, wiFiNetwork);
            setTitle(TextUtils.isEmpty(wiFiNetwork.getSsidName()) ? wiFiNetwork.getMacAddress() : wiFiNetwork.getSsidName());

            NetworkFragment fragment = new NetworkFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.keygen_fragment, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(
                        this, new Intent(this, ManualInputActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                );
                return true;
            case R.id.pref:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.preferences, menu);
        return true;
    }
}

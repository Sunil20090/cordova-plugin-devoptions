package cordova.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.provider.Settings;

public class DevOptions extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("isDevOptionsEnabled".equals(action)) {
            boolean enabled = isDeveloperOptionsEnabled();
            callbackContext.success(enabled ? 1 : 0); // send 1 or 0 back
            return true;
        }
        return false;
    }

    private boolean isDeveloperOptionsEnabled() {
        try {
            int devSettings = Settings.Global.getInt(
                cordova.getActivity().getContentResolver(),
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
            return devSettings == 1;
        } catch (Settings.SettingNotFoundException e) {
            return false;
        }
    }
}

package com.ecom.common.theme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * DOCUMENT_ME
 *
 * @author Oleg Varaksin / last modified by $Author: ovaraksin@gmail.com $
 * @version $Revision: 1223 $
 */
public class AvailableThemes {

    private final HashMap<String, Theme> themesAsMap;
    private final List<Theme> themes;
    private static AvailableThemes instance = null;

    public static AvailableThemes getInstance() {
        if (instance == null) {
            instance = new AvailableThemes();
        }

        return instance;
    }

    private AvailableThemes() {
        final List<String> themeNames = new ArrayList<String>();
        themeNames.add("redmond");
        themeNames.add("blitzer");
        themeNames.add("flick");
        themeNames.add("hot-sneaks");
        themeNames.add("black-tie");
        themeNames.add("excite-bike");
        themeNames.add("glass-x");
        themeNames.add("home");
        themeNames.add("overcast");
        themeNames.add("pepper-grinder");
        themeNames.add("rocket");
        themeNames.add("sam");
        themeNames.add("sunny");
        themeNames.add("smoothness");
        themeNames.add("south-street");
        themeNames.add("start");
        themeNames.add("ui-lightness");
        themeNames.add("bootstrap");
        themesAsMap = new HashMap<String, Theme>();
        themes = new ArrayList<Theme>();

        for (final String themeName : themeNames) {
            final Theme theme = new Theme();
            theme.setName(themeName);
            theme.setImage("/resources/images/themeswitcher/" + themeName + ".png");

            themes.add(theme);
            themesAsMap.put(theme.getName(), theme);
        }
    }

    public final List<Theme> getThemes() {
        return themes;
    }

    public Theme getThemeForName(final String name) {
        return themesAsMap.get(name);
    }
}

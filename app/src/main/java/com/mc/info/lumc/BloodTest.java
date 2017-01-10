package com.mc.info.lumc;

import java.util.HashMap;

public abstract class BloodTest extends Examination {
    public BloodTest() {}

    public abstract HashMap<String, String> toHashMap();
}

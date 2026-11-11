package com.egorik.util;

public class Animation {
    private float value, target, speed = 0.15f;

    public void setTarget(float t) { this.target = t; }
    public float get() { return value; }

    public void update() {
        value += (target - value) * speed;
        if (Math.abs(target - value) < 0.001f) value = target;
    }

    public static float easeOutCubic(float x) {
        return 1f - (float)Math.pow(1f - x, 3);
    }
}

package com.techyourchance.mvc.screens.common.controllers;

public interface BackPressedListener {
    /**
     * @return true if listener handled the back pressed; false otherwise
     */
    boolean onBackPressed();
}

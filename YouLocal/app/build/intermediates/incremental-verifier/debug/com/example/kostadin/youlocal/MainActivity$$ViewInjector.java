// Generated code from Butter Knife. Do not modify!
package com.example.kostadin.youlocal;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MainActivity$$ViewInjector<T extends com.example.kostadin.youlocal.MainActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492973, "field 'usernameText'");
    target.usernameText = finder.castView(view, 2131492973, "field 'usernameText'");
    view = finder.findRequiredView(source, 2131492975, "field 'passwordText'");
    target.passwordText = finder.castView(view, 2131492975, "field 'passwordText'");
    view = finder.findRequiredView(source, 2131492976, "field 'loginButton'");
    target.loginButton = finder.castView(view, 2131492976, "field 'loginButton'");
    view = finder.findRequiredView(source, 2131492977, "field 'forgottenPass'");
    target.forgottenPass = finder.castView(view, 2131492977, "field 'forgottenPass'");
    view = finder.findRequiredView(source, 2131492972, "field 'userNameLayout'");
    target.userNameLayout = finder.castView(view, 2131492972, "field 'userNameLayout'");
    view = finder.findRequiredView(source, 2131492974, "field 'passwordLayout'");
    target.passwordLayout = finder.castView(view, 2131492974, "field 'passwordLayout'");
  }

  @Override public void reset(T target) {
    target.usernameText = null;
    target.passwordText = null;
    target.loginButton = null;
    target.forgottenPass = null;
    target.userNameLayout = null;
    target.passwordLayout = null;
  }
}

import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../domain/app/models/app_settings.dart';
import '../../../../domain/app/view_models/app_settings_view_model.dart';
import '../../../../domain/user/managers/user_session_manager.dart';
import '../../../../domain/user/services/user_service.dart';
import '../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../domain/user/view_models/user_login_infos_view_model.dart';
import '../../../../infra/logging/log_appender_database.dart';
import '../../../../infra/logging/logger.dart';
import '../../../../infra/sqlite/app_database.dart';
import '../../../l10n/app_localizations.dart';
import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/app_theme_extension.dart';
import '../../../themes/sizes.dart';
import '../../components/index.dart';

class LoginForm extends ConsumerStatefulWidget {
  const LoginForm({super.key});

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _LoginFormState();
}

class _LoginFormState extends ConsumerState<LoginForm> {
  final _formKey = GlobalKey<FormState>();

  bool _isWaitingLoginRequest = false;

  late Int64 _userId;
  late String _password;
  bool? _rememberMe;

  LogAppenderDatabase? _logAppenderDatabase;

  @override
  Widget build(BuildContext context) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final appSettings = ref.watch(appSettingsViewModel)!;
    final userLoginInfos = ref.watch(userLoginInfosViewModel);
    ref.listen(loggedInUserViewModel, (_, loggedInUser) {
      final appender = _logAppenderDatabase;
      if (loggedInUser == null && appender != null) {
        logger.removeAppender(appender);
        _logAppenderDatabase = null;
      }
    });
    return _buildView(
      context.theme,
      appLocalizations,
      appSettings,
      userLoginInfos,
    );
  }

  void _submit() {
    if (_isWaitingLoginRequest || !_formKey.currentState!.validate()) {
      return;
    }
    _formKey.currentState!.save();
    _isWaitingLoginRequest = true;
    setState(() {});
    _login();
  }

  Future<void> _login() async {
    final user = await UserService.login(_userId);
    userSessionManager = UserSessionManager(userId: _userId);
    await userSessionManager.onLoggedIn(
      ref: ref,
      rememberMe: _rememberMe!,
      user: user,
      password: _password,
    );
    if (!mounted) {
      return;
    }
    _isWaitingLoginRequest = false;
    setState(() {});
  }

  void _setUserId(String? newValue) {
    if (newValue != null) {
      _userId = Int64.parseInt(newValue);
    }
  }

  void _setPassword(String? newValue) {
    if (newValue != null) {
      _password = newValue;
    }
  }
}

extension _LoginFormView on _LoginFormState {
  Widget _buildView(
    ThemeData theme,
    AppLocalizations appLocalizations,
    AppSettings appSettings,
    List<UserLoginInfoTableData> userLoginInfos,
  ) {
    final localizations = appLocalizations;
    _rememberMe ??= appSettings.getRememberMe() ?? false;
    final userLoginInfo = userLoginInfos.firstOrNull;
    final borderError = UnderlineInputBorder(
      borderSide: BorderSide(color: theme.colorScheme.error),
    );
    return FocusScope(
      onKeyEvent: (node, event) {
        if (event is KeyDownEvent &&
            event.logicalKey == LogicalKeyboardKey.enter) {
          _submit();
          return KeyEventResult.handled;
        }
        return KeyEventResult.ignored;
      },
      child: Form(
        key: _formKey,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            TextFormField(
              autofocus: true,
              initialValue: userLoginInfo?.userId.toString(),
              cursorColor: theme.primaryColor,
              // Length for the max digit of 8-bytes number
              maxLength: 20,
              keyboardType: TextInputType.number,
              inputFormatters: [FilteringTextInputFormatter.digitsOnly],
              textAlignVertical: TextAlignVertical.center,
              decoration: InputDecoration(
                // fix height regardless of whether or not an error is displayed.
                helperText: ' ',
                // hide length counter
                counterText: '',
                prefixIcon: Icon(
                  Symbols.person_outline_rounded,
                  color: theme.inputDecorationTheme.iconColor,
                ),
                // color: ThemeConfig.textColorSecondary),
                isCollapsed: true,
                contentPadding: Sizes.paddingV16,
                enabledBorder: UnderlineInputBorder(
                  borderSide: BorderSide(color: theme.dividerColor),
                ),
                focusedBorder: UnderlineInputBorder(
                  borderSide: BorderSide(color: theme.primaryColor),
                ),
                errorBorder: borderError,
                focusedErrorBorder: borderError,
                hintText: localizations.userId,
              ),
              onFieldSubmitted: (value) => _submit(),
              onSaved: _setUserId,
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return localizations.pleaseEnterUserId;
                }
                return null;
              },
            ),
            TextFormField(
              initialValue: userLoginInfo?.password,
              cursorColor: theme.primaryColor,
              obscureText: true,
              textAlignVertical: TextAlignVertical.center,
              decoration: InputDecoration(
                // fix height regardless of whether or not an error is displayed.
                helperText: ' ',
                // contentPadding: EdgeInsets.only(bottom: 8),
                prefixIcon: Icon(
                  Symbols.lock_outline_rounded,
                  color: theme.inputDecorationTheme.iconColor,
                ),
                isCollapsed: true,
                contentPadding: Sizes.paddingV16,
                enabledBorder: UnderlineInputBorder(
                  borderSide: BorderSide(color: theme.dividerColor),
                ),
                focusedBorder: UnderlineInputBorder(
                  borderSide: BorderSide(color: theme.primaryColor),
                ),
                errorBorder: borderError,
                focusedErrorBorder: borderError,
                hintText: localizations.userPassword,
              ),
              onFieldSubmitted: (value) => _submit(),
              onSaved: _setPassword,
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return localizations.pleaseEnterPassword;
                }
                return null;
              },
            ),
            Sizes.sizedBoxH12,
            TCheckbox(
              _rememberMe!,
              localizations.rememberMe,
              onCheckedChanged: (bool checked) {
                _rememberMe = checked;
              },
            ),
            Sizes.sizedBoxH32,
            _buildLoginButton(_isWaitingLoginRequest, localizations, theme),
          ],
        ),
      ),
    );
  }

  Widget _buildLoginButton(
    bool isWaitingLoginRequest,
    AppLocalizations localizations,
    ThemeData theme,
  ) => FilledButton(
    onPressed: isWaitingLoginRequest ? null : _submit,
    style: FilledButton.styleFrom(
      minimumSize: const Size(0, 56),
      shape: const RoundedRectangleBorder(
        borderRadius: Sizes.borderRadiusCircular4,
      ),
      disabledBackgroundColor: theme.disabledColor,
    ),
    child: isWaitingLoginRequest
        ? const SizedBox(
            height: 24,
            width: 24,
            child: RepaintBoundary(
              child: CircularProgressIndicator(color: Colors.white),
            ),
          )
        : Text(
            localizations.login,
            style: theme.textTheme.labelMedium!.copyWith(
              fontSize: 20,
              color: Colors.white,
            ),
          ),
  );
}

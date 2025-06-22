import 'package:flutter/material.dart';

import '../../../../themes/sizes.dart';
import '../../../components/index.dart';
import 'setting_form_field_groups.dart';
import 'settings_pane.dart';
import 'sub_navigation_rail.dart';

/// UI design: We don't put all settings separated by groups in one view because:
/// 1. It seems messy.
/// 2. (James Chen) my mouse wheel always break in just few months after I bought them,
/// which make me avoiding designing scrollable UI.
class SettingsPage extends StatefulWidget {
  const SettingsPage({super.key});

  @override
  State<SettingsPage> createState() => _SettingsPageState();
}

class _SettingsPageState extends State<SettingsPage> {
  @override
  Widget build(BuildContext context) => SizedBox(
    width: Sizes.dialogWidthMedium,
    height: Sizes.dialogHeightMedium,
    child: Row(
      children: [
        SubNavigationRail(onTabSelected: (index, tab) => _selectTab(tab)),
        Expanded(
          child: Column(
            children: [
              const Align(
                alignment: Alignment.topRight,
                child: TTitleBar(
                  displayCloseOnly: true,
                  popOnCloseTapped: true,
                  usePositioned: false,
                ),
              ),
              Expanded(
                child: SettingsPane(
                  onSettingFormFieldGroupScrolled: (index) {
                    _selectTabWithoutScroll();
                  },
                ),
              ),
            ],
          ),
        ),
      ],
    ),
  );

  void _selectTab(TTab tab) {
    final fieldGroupContext =
        formFieldGroupToContext[tab.id as SettingFormFieldGroup]
            ?.key
            .currentContext;
    if (fieldGroupContext != null) {
      Scrollable.ensureVisible(
        fieldGroupContext,
        duration: const Duration(milliseconds: 100),
        curve: Curves.fastOutSlowIn,
      );
    }
    setState(() {});
  }

  void _selectTabWithoutScroll() {
    setState(() {});
  }
}

Future<void> showSettingsDialog(BuildContext context) => showCustomTDialog(
  routeName: '/settings-dialog',
  context: context,
  child: const SettingsPage(),
);

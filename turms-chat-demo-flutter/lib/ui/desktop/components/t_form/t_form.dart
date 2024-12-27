import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../../../themes/index.dart';
import '../index.dart';

class TForm extends StatelessWidget {
  const TForm({super.key, required this.formData});

  final TFormData formData;

  @override
  Widget build(BuildContext context) => Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: formData.groups.indexed.expand((item) {
          final (index, group) = item;
          final children = <Widget>[];
          if (index > 0) {
            children.add(const Padding(
              padding: Sizes.paddingV16,
              // child: SizedBox.shrink(),
              child: THorizontalDivider(),
            ));
          }
          final text = Text(group.title,
              key: group.titleKey, style: const TextStyle(fontSize: 16));
          final titleSuffix = group.titleSuffix;
          if (titleSuffix == null) {
            children.add(text);
          } else {
            children.add(Row(spacing: 16, children: [text, titleSuffix]));
          }
          for (final element in group.fields.indexed) {
            final (index, field) = element;
            for (final widget in _buildFormField(index, field)) {
              children.add(widget);
            }
          }
          return children;
        }).toList(),
      );

  List<Widget> _buildFormField(int index, TFormField field) =>
      <Widget>[Sizes.sizedBoxH8] +
      switch (field) {
        TFormFieldCheckbox() => <Widget>[
            TSimpleCheckbox(
                onChanged: field.onChanged,
                value: field.value,
                label: field.label)
          ],
        TFormFieldRadioGroup() => <Widget>[
            Wrap(
              direction: Axis.vertical,
              spacing: 8,
              children: [
                Text(field.label),
                Wrap(direction: Axis.vertical, spacing: 8, children: <Widget>[
                  for (final radio in field.radios)
                    TRadio(
                      value: radio.value,
                      groupValue: field.groupValue,
                      label: radio.label,
                      onChanged: (value) {
                        // TODO: Wait for "declaration-site variance"
                        // https://github.com/dart-lang/language/issues/524
                        (radio as dynamic)?.onChanged?.call(value);
                        (field as dynamic)?.onChanged?.call(value);
                      },
                    )
                ])
              ],
            ),
          ],
        TFormFieldShortcutTextField() => <Widget>[
            Row(spacing: 16, mainAxisSize: MainAxisSize.min, children: [
              Text(field.label),
              SizedBox(
                width: 180,
                child: TShortcutTextField(
                  initialKeys: field.initialKeys,
                  onShortcutChanged: field.onShortcutChanged,
                ),
              )
            ])
          ],
        TFormFieldSelect() => <Widget>[
            Row(mainAxisSize: MainAxisSize.min, children: [
              Text(field.label),
              const SizedBox(
                width: 16,
              ),
              SizedBox(
                width: 180,
                child: TTextFieldMenuPopup(
                  value: field.value,
                  entries: field.entries,
                  onSelected: (TMenuEntry item) {
                    // TODO: Wait for "declaration-site variance"
                    // https://github.com/dart-lang/language/issues/524
                    (field as dynamic).onSelected.call(item.value);
                  },
                ),
              )
            ])
          ],
        _ => <Widget>[]
      };
}

class TFormData {
  TFormData({required this.groups});

  final List<TFormFieldGroup> groups;
}

class TFormFieldGroup {
  TFormFieldGroup(
      {required this.title,
      this.titleSuffix,
      this.titleKey,
      required this.fields});

  final String title;
  final Widget? titleSuffix;
  final Key? titleKey;
  final List<TFormField> fields;
}

class TFormField {
  TFormField({required this.label});

  final String label;
}

class TFormFieldCheckbox extends TFormField {
  TFormFieldCheckbox(
      {required super.label, required this.value, required this.onChanged});

  final bool value;

  final ValueChanged<bool> onChanged;
}

class TFormFieldRadioGroup<T> extends TFormField {
  TFormFieldRadioGroup(
      {required super.label,
      required this.groupValue,
      required this.radios,
      this.onChanged});

  final T groupValue;

  final List<TFormFieldRadio<T>> radios;

  final ValueChanged<T>? onChanged;
}

class TFormFieldRadio<T> {
  TFormFieldRadio({required this.label, required this.value, this.onChanged});

  final String label;

  final T value;

  final ValueChanged<T>? onChanged;
}

class TFormFieldShortcutTextField extends TFormField {
  TFormFieldShortcutTextField(
      {required super.label,
      this.initialKeys,
      required this.onShortcutChanged});

  final List<LogicalKeyboardKey>? initialKeys;
  final void Function(List<LogicalKeyboardKey> keys) onShortcutChanged;
}

class TFormFieldSelect<T> extends TFormField {
  TFormFieldSelect(
      {required super.label,
      this.value,
      required this.entries,
      required this.onSelected});

  final T? value;
  final List<TMenuEntry<T>> entries;
  final void Function(T value) onSelected;
}

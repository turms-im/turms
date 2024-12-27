import 'package:flutter/material.dart';

import '../../components/t_layout/t_responsive_layout.dart';
import 'home_page_landscape.dart';
import 'home_page_portrait.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) => const TResponsiveLayout(
        portraitLayoutContent: HomePagePortrait(),
        landscapeLayoutContent: HomePageLandscape(),
      );
}

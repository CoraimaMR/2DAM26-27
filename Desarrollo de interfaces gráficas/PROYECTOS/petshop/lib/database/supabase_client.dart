import 'package:supabase/supabase.dart';

// link de la base de datos: https://supabase.com/dashboard/project/yjlyjvzcareczjytebnp
class DatabaseConfig{
  static const url= "https://yjlyjvzcareczjytebnp.supabase.co";
  static const anonKey ="sb_publishable_V2ANfDmSVGPUAHrEB0JnaQ_h9woXhek";
  static final client = SupabaseClient(url, anonKey);
}

  // dart pub add supabase -> Se instala la dependencia supabase
  // dart pub get -> Se descargan las dependencias
package com.tilldawn.Model;

public enum Language {
    English("English","Similar keys for two commands!", "Please enter your username.",
        "Please answer the question.", "The answer you entered is incorrect.", "Please enter your new password",
        "Password too easy", "The fields are essential", "Character ", "Weapon ", " selected.", "Time adjusted to ",
        "minutes", "The username must contain only a-zA-z0-9_", "No file selected", "Kills", "Username", "Score",
        "Playtime", "Level", "Your favorite movie?", "Your first partner's name?", "Your email address?",
        "Your favorite food?", "Your favorite character?", "Your favorite color?", "Random randy orton",
        "20 Minutes Till Dawn", "Revolver", "Shotgun", "SMGs Dual", "Username not found.", "Password doesn't match",
        "Password changed successfully", "Something went wrong.", "Successfully changed username!",
        "Successfully changed info!", "User successfully deleted!", "Successfully changed avatar!", "Vitality", "Damager",
        "Procrease", "Amocrease", "Speedy", "Next Level", "Up", "Down", "Left", "Right", "Auto Aim", "Reload", "Main Menu",
        "Congrats you survived", "Loser...", "Click to end.", "Find User", "Authorize answer", "New Password",
        "Change password", "Sign Up", "Password", "Forgot Password?", "Continue As Guest", "Login", "Setting Menu",
        "Pre-Game Menu", "Scoreboard Menu", "Talent Menu", "Login Menu", "Logout", "Profile Menu", "Load Game", "Resume",
        "Save", "Give Up", "Grayscale", "Start", "Change Avatar", "Delete Account", "Change Info", "Apply",
        "Choose Local Avatar", "Music Volume", "Music Track", "SFXs", "Control Setting", "Auto Reload"),
    Spanish("Español","¡Teclas similares para dos comandos!", "Por favor, introduce tu nombre de usuario.",
        "Por favor, responde a la pregunta.", "La respuesta que has introducido es incorrecta.", "Por favor, introduce tu nueva contraseña",
        "Contraseña demasiado fácil", "Los campos son esenciales", "Personaje", "Arma", "seleccionado.", "Tiempo ajustado a",
        "minutos", "El nombre de usuario debe contener solo a-zA-z0-9_", "No se ha seleccionado ningún archivo", "Victorias", "usuario", "Puntuación",
        "Tiempo de juego", "Nivel", "¿Tu película favorita?", "¿Nombre de tu primer compañero?", "¿Tu correo electrónico?",
        "¿Tu comida favorita?", "¿Tu personaje favorito?", "¿Tu color favorito?", "Randy Orton aleatorio",
        "20 minutos para el amanecer", "Revólver", "Escopeta", "Subfusiles dobles", "Nombre de usuario no encontrado", "La contraseña no coincide",
        "Contraseña cambiada correctamente", "Algo salió mal ¡Incorrecto!", "¡Usuario cambiado correctamente!",
        "¡Información cambiada correctamente!", "¡Usuario eliminado correctamente!", "¡Avatar cambiado correctamente!", "Vitalidad", "Dañador",
        "Procrease", "Amocrease", "Velocidad", "Siguiente nivel", "Arriba", "Abajo", "Izquierda", "Derecha", "Apuntado automático", "Recargar", "Menú principal",
        "¡Felicidades, sobreviviste!", "Perdedor...", "Haz clic para terminar.", "Buscar usuario", "Autorizar respuesta", "Nueva contraseña",
        "Cambiar contraseña", "Registrarse", "Contraseña", "¿Olvidaste tu contraseña?", "Continuar como invitado", "Iniciar sesión", "Menú de configuración",
        "Menú previo al juego", "Menú del marcador", "Menú de talentos", "Menú de inicio de sesión", "Cerrar sesión", "Menú de perfil", "Cargar partida", "Reanudar",
        "Guardar", "Renunciar", "Escala de grises", "Iniciar", "Cambiar avatar", "Eliminar cuenta", "Cambiar información", "Aplicar", "Elegir avatar local", "Volumen de la música", "Pista de música", "Efectos de sonido", "Configuración de control", "Recarga automática");
    private final String name;
    public final String SimilarKeysError;
    public final String NoUsernameError;
    public final String NoAnswerError;
    public final String IncorrectAnswerError;
    public final String NoNewPasswordError;
    public final String EasyPasswordError;
    public final String EmptyFieldError;
    public final String Character;
    public final String Weapon;
    public final String Selected;
    public final String TimeAdjusted;
    public final String Minutes;
    public final String InvalidUsernameError;
    public final String NoFileSelectedError;
    public final String Kills;
    public final String Username;
    public final String Score;
    public final String Playtime;
    public final String Level;
    public final String Q1;
    public final String Q2;
    public final String Q3;
    public final String Q4;
    public final String Q5;
    public final String Q6;
    public final String Q7;
    public final String TMTD;
    public final String Revolver;
    public final String Shotgun;
    public final String DualSMGs;
    public final String UsernameNotFoundError;
    public final String WrongPasswordError;
    public final String PasswordChanged;
    public final String SomethingWentWrong;
    public final String UsernameChanged;
    public final String InfoChanged;
    public final String UserDeleted;
    public final String AvatarChanged;
    public final String Vitality;
    public final String Damager;
    public final String Procrease;
    public final String Amocrease;
    public final String Speedy;
    public final String NextLevel;
    public final String Up;
    public final String Down;
    public final String Left;
    public final String Right;
    public final String AutoAim;
    public final String Reload;
    public final String MainMenu;
    public final String Congrats;
    public final String Loser;
    public final String ClickToEnd;
    public final String FindUser;
    public final String AuthorizeAnswer;
    public final String NewPassword;
    public final String ChangePassword;
    public final String SignUp;
    public final String Password;
    public final String ForgotPassword;
    public final String ContinueAsGuest;
    public final String Login;
    public final String SettingMenu;
    public final String PreGameMenu;
    public final String ScoreboardMenu;
    public final String TalentMenu;
    public final String LoginMenu;
    public final String Logout;
    public final String ProfileMenu;
    public final String LoadGame;
    public final String Resume;
    public final String Save;
    public final String GiveUp;
    public final String Grayscale;
    public final String Start;
    public final String ChangeAvatar;
    public final String DeleteAccount;
    public final String ChangeInfo;
    public final String Apply;
    public final String ChooseLocalAvatar;
    public final String MusicVolume;
    public final String MusicTrack;
    public final String SFXs;
    public final String ControlSetting;
    public final String AutoReload;


    Language(String name,
             String SimilarKeysError,
             String NoUsernameError,
             String NoAnswerError,
             String IncorrectAnswerError,
             String NoNewPasswordError,
             String EasyPasswordError,
             String EmptyFieldError,
             String Character,
             String Weapon,
             String Selected,
             String TimeAdjusted,
             String Minutes,
             String InvalidUsernameError,
             String NoFileSelectedError,
             String Kills,
             String Username,
             String Score,
             String Playtime,
             String Level,
             String Q1,
             String Q2,
             String Q3,
             String Q4,
             String Q5,
             String Q6,
             String Q7,
             String TMTD,
             String Revolver,
             String Shotgun,
             String DualSMGs,
             String UsernameNotFoundError,
             String WrongPasswordError,
             String PasswordChanged,
             String SomethingWentWrong,
             String UsernameChanged,
             String InfoChanged,
             String UserDeleted,
             String AvatarChanged,
             String Vitality,
             String Damager,
             String Procrease,
             String Amocrease,
             String Speedy,
             String NextLevel,
             String Up,
             String Down,
             String Left,
             String Right,
             String AutoAim,
             String Reload,
             String MainMenu,
             String Congrats,
             String Loser,
             String ClickToEnd,
             String FindUser,
             String AuthorizeAnswer,
             String NewPassword,
             String ChangePassword,
             String SignUp,
             String Password,
             String ForgotPassword,
             String ContinueAsGuest,
             String Login,
             String SettingMenu,
             String PreGameMenu,
             String ScoreboardMenu,
             String TalentMenu,
             String LoginMenu,
             String Logout,
             String ProfileMenu,
             String LoadGame,
             String Resume,
             String Save,
             String GiveUp,
             String Grayscale,
             String Start,
             String ChangeAvatar,
             String DeleteAccount,
             String ChangeInfo,
             String Apply,
             String ChooseLocalAvatar,
             String MusicVolume,
             String MusicTrack,
             String SFXs,
             String ControlSetting,
             String AutoReload) {

        this.name = name;
        this.SimilarKeysError = SimilarKeysError;
        this.NoUsernameError = NoUsernameError;
        this.NoAnswerError = NoAnswerError;
        this.IncorrectAnswerError = IncorrectAnswerError;
        this.NoNewPasswordError = NoNewPasswordError;
        this.EasyPasswordError = EasyPasswordError;
        this.EmptyFieldError = EmptyFieldError;
        this.Character = Character;
        this.Weapon = Weapon;
        this.Selected = Selected;
        this.TimeAdjusted = TimeAdjusted;
        this.Minutes = Minutes;
        this.InvalidUsernameError = InvalidUsernameError;
        this.NoFileSelectedError = NoFileSelectedError;
        this.Kills = Kills;
        this.Username = Username;
        this.Score = Score;
        this.Playtime = Playtime;
        this.Level = Level;
        this.Q1 = Q1;
        this.Q2 = Q2;
        this.Q3 = Q3;
        this.Q4 = Q4;
        this.Q5 = Q5;
        this.Q6 = Q6;
        this.Q7 = Q7;
        this.TMTD = TMTD;
        this.Revolver = Revolver;
        this.Shotgun = Shotgun;
        this.DualSMGs = DualSMGs;
        this.UsernameNotFoundError = UsernameNotFoundError;
        this.WrongPasswordError = WrongPasswordError;
        this.PasswordChanged = PasswordChanged;
        this.SomethingWentWrong = SomethingWentWrong;
        this.UsernameChanged = UsernameChanged;
        this.InfoChanged = InfoChanged;
        this.UserDeleted = UserDeleted;
        this.AvatarChanged = AvatarChanged;
        this.Vitality = Vitality;
        this.Damager = Damager;
        this.Procrease = Procrease;
        this.Amocrease = Amocrease;
        this.Speedy = Speedy;
        this.NextLevel = NextLevel;
        this.Up = Up;
        this.Down = Down;
        this.Left = Left;
        this.Right = Right;
        this.AutoAim = AutoAim;
        this.Reload = Reload;
        this.MainMenu = MainMenu;
        this.Congrats = Congrats;
        this.Loser = Loser;
        this.ClickToEnd = ClickToEnd;
        this.FindUser = FindUser;
        this.AuthorizeAnswer = AuthorizeAnswer;
        this.NewPassword = NewPassword;
        this.ChangePassword = ChangePassword;
        this.SignUp = SignUp;
        this.Password = Password;
        this.ForgotPassword = ForgotPassword;
        this.ContinueAsGuest = ContinueAsGuest;
        this.Login = Login;
        this.SettingMenu = SettingMenu;
        this.PreGameMenu = PreGameMenu;
        this.ScoreboardMenu = ScoreboardMenu;
        this.TalentMenu = TalentMenu;
        this.LoginMenu = LoginMenu;
        this.Logout = Logout;
        this.ProfileMenu = ProfileMenu;
        this.LoadGame = LoadGame;
        this.Resume = Resume;
        this.Save = Save;
        this.GiveUp = GiveUp;
        this.Grayscale = Grayscale;
        this.Start = Start;
        this.ChangeAvatar = ChangeAvatar;
        this.DeleteAccount = DeleteAccount;
        this.ChangeInfo = ChangeInfo;
        this.Apply = Apply;
        this.ChooseLocalAvatar = ChooseLocalAvatar;
        this.MusicVolume = MusicVolume;
        this.MusicTrack = MusicTrack;
        this.SFXs = SFXs;
        this.ControlSetting = ControlSetting;
        this.AutoReload = AutoReload;
    }


    public static Language findLanguage(String language) {
        for (Language value : Language.values()) {
            if (value.getName().equals(language)) {return value;}
        }
        return null;
    }

    public String getName() {
        return name;
    }

}

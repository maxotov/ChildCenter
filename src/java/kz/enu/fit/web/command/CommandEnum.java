package kz.enu.fit.web.command;

import kz.enu.fit.web.command.impl.About;
import kz.enu.fit.web.command.impl.AddNews;
import kz.enu.fit.web.command.impl.ChangeLocale;
import kz.enu.fit.web.command.impl.DeleteNews;
import kz.enu.fit.web.command.impl.DeleteComment;
import kz.enu.fit.web.command.impl.EditNews;
import kz.enu.fit.web.command.impl.LogoutCommand;
import kz.enu.fit.web.command.impl.LoginCommand;
import kz.enu.fit.web.command.impl.Registration;
import kz.enu.fit.web.command.impl.SaveNews;
import kz.enu.fit.web.command.impl.AddForumCommand;
import kz.enu.fit.web.command.impl.ShowCenter;
import kz.enu.fit.web.command.impl.ShowListNews;
import kz.enu.fit.web.command.impl.ShowListComment;
import kz.enu.fit.web.command.impl.ShowNews;
import kz.enu.fit.web.command.impl.AddComment;
import kz.enu.fit.web.command.impl.AddForumAnswerCommand;
import kz.enu.fit.web.command.impl.EditCenterCommand;
import kz.enu.fit.web.command.impl.FeedbackCommand;
import kz.enu.fit.web.command.impl.QuizCommand;
import kz.enu.fit.web.command.impl.ShowAdviceCommand;
import kz.enu.fit.web.command.impl.ShowComments;
import kz.enu.fit.web.command.impl.ShowForumCommand;
import kz.enu.fit.web.command.impl.ShowLawCommand;
import kz.enu.fit.web.command.impl.UpdateForumStatusCommand;

public enum CommandEnum {

    LOGIN {
        {
            this.Command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.Command = new LogoutCommand();
        }
    },
    REGISTER {
        {
            this.Command = new Registration();
        }
    },
    LOCALE {
        {
            this.Command = new ChangeLocale();
        }
    },
    PAGE {
        {
            this.Command = new About();
        }
    },
    ADD_FORUM {
        {
            this.Command = new AddForumCommand();
        }
    },
    ADD_OTZIV {
        {
            this.Command = new AddComment();
        }
    },
    DATA {
        {
            this.Command = new ShowNews();
        }
    },
    NEWSADD {
        {
            this.Command = new AddNews();
        }
    },
    SHOWOTZIV {
        {
            this.Command = new ShowListComment();
        }
    },
    DELETE {
        {
            this.Command = new DeleteComment();
        }
    },
    SHOWNEWS {
        {
            this.Command = new ShowListNews();
        }
    },
    DELDATA {
        {
            this.Command = new DeleteNews();
        }
    },
    EDIT {
        {
            this.Command = new EditNews();
        }
    },
    SAVENEWS {
        {
            this.Command = new SaveNews();
        }
    },
    SHOWCOMMENTS {
        {
            this.Command = new ShowComments();
        }
    },
    CENTER {
        {
            this.Command = new ShowCenter();
        }
    },    
    EDIT_CENTER {
        {
            this.Command = new EditCenterCommand();
        }
    },    
    SHOW_FORUM {
        {
            this.Command = new ShowForumCommand();
        }
    },    
    ADD_ANSWER {
        {
            this.Command = new AddForumAnswerCommand();
        }
    },    
    UPDATE_FORUM_STATUS {
        {
            this.Command = new UpdateForumStatusCommand();
        }
    },    
    QUIZ {
        {
            this.Command = new QuizCommand();
        }
    },
    SHOW_ADVICE{
        {
            this.Command = new ShowAdviceCommand();
        }
    },
    FEEDBACK{
        {
            this.Command = new FeedbackCommand();
        }
    },
    SHOW_LAW {
        {
            this.Command = new ShowLawCommand();
        }
    };
    
    protected ActionCommand Command;

    /**
     * receives from enum of command
     *
     * @return command
     */
    public ActionCommand getCurrentCommand() {
        return Command;
    }
}

package krishna.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import krishna.bean.ChatRoom;
import krishna.bean.ChatRoomList;


public class ManageChatServlet
  extends javax.servlet.http.HttpServlet
{
  ChatRoomList rooms;
  Properties props;
  
  public ManageChatServlet() { jdMethod_this(); }
  
  private final void jdMethod_this() { rooms = new ChatRoomList();
    props = null;
  }
  
  public void init() throws ServletException
  {
    try
    {
      String str1 = "";
      str1 = "/WEB-INF/" + getServletContext().getInitParameter("chatpropertyfile");
      
      String str2 = getServletContext().getRealPath(str1);
      if (str2 != null)
      {
        java.io.FileInputStream localFileInputStream = new java.io.FileInputStream(str2);
        
        props = new Properties();
        props.load(localFileInputStream);
        java.util.Enumeration localEnumeration = props.keys();
        while (localEnumeration.hasMoreElements())
        {
          String str3 = (String)localEnumeration.nextElement();
          String str4 = props.getProperty(str3);
          addNewRoom(rooms, str3, str4);
        }
        localFileInputStream.close();
        getServletContext().setAttribute("chatroomlist", rooms);
      }
      else
      {
        System.out.println("error " + str1);
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      System.err.println("Properites file not found:" + localFileNotFoundException.getMessage());
    }
    catch (IOException localIOException)
    {
      System.out.print("Unable to load Properties File: " + localIOException.getMessage());
    }
  }
  
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException
  {
    paramHttpServletResponse.setContentType("text/html");
    PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
    localPrintWriter.println("Room List Created");
    localPrintWriter.close();
  }
  


  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    String str1 = paramHttpServletRequest.getParameter("rn");
    String str2 = paramHttpServletRequest.getParameter("rd");
    if ((str1 == null) || ((str1 = str1.trim()).length() == 0) || (str2 == null) || ((str2 = str2.trim()).length() == 0))
    {
      paramHttpServletRequest.setAttribute("error", "");
     // getServletContext().getRequestDispatcher("/addRoom.jsp").forward(paramHttpServletRequest, paramHttpServletResponse);
      return;
    }
    
    if ((str1 != null) && (str1.indexOf(" ") != -1))
    {
      paramHttpServletRequest.setAttribute("error", "");
      //getServletContext().getRequestDispatcher("/addRoom.jsp").forward(paramHttpServletRequest, paramHttpServletResponse);
      return;
    }
    try
    {
      if (rooms != null)
      {
        addNewRoom(rooms, str1, str2);
      }
      

      String str3 = getServletContext().getInitParameter("saveRooms");
      boolean bool = false;
      Object localObject; if ((str3 != null) && ("true".equals(str3)))
      {
        localObject = Boolean.valueOf(str3);
        bool = ((Boolean)localObject).booleanValue();
      }
      if (bool)
      {
        if (props != null)
        {
          props.put(str1, str2);
          localObject = "/WEB-INF/" + getServletContext().getInitParameter("chatpropertyfile");
          String str4 = getServletContext().getRealPath((String)localObject);
          java.io.FileOutputStream localFileOutputStream = new java.io.FileOutputStream(str4);
          props.store(localFileOutputStream, "Chat");
          localFileOutputStream.close();
        }
        else
        {
          paramHttpServletResponse.setContentType("text/html");
          localObject = paramHttpServletResponse.getWriter();
          ((PrintWriter)localObject).println("Properties are null");
        }
      }
    }
    catch (Exception localException)
    {
      System.err.println("Exception: " + localException.getMessage());
    }
  }
  


  public void addNewRoom(ChatRoomList paramChatRoomList, String paramString1, String paramString2)
  {
    String str = getServletContext().getInitParameter("maxNoOfMessages");
    int i = 25;
    if (str != null)
    {
      try
      {
        i = Integer.parseInt(str);
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    


    ChatRoom localChatRoom = new ChatRoom(paramString1, paramString2);
    localChatRoom.setMaximumNoOfMessages(i);
    rooms.addRoom(localChatRoom);
  }
  


  public void destroy()
  {
    System.err.println("Destroying Chat");
  }
}

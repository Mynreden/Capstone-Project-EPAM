package com.example.capstoneproject.controllers;

import com.example.capstoneproject.domain.User;
import com.example.capstoneproject.services.CartService;
import com.example.capstoneproject.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FrontendControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private CartService cartService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private FrontendController frontendController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testRenderSignPage_UserSignedIn() {
        User user = new User();

        when(session.getAttribute("user")).thenReturn(user);

        String viewName = frontendController.renderSignPage(model, session);

        assertEquals("redirect:/", viewName);
    }

    @Test
    public void testRenderSignPage_UserNotSignedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String viewName = frontendController.renderSignPage(model, session);

        assertEquals("sign_in", viewName);
    }

    @Test
    public void testRenderCartPage_UserNotSignedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String viewName = frontendController.renderCartPage(model, session, redirectAttributes);

        assertEquals("redirect:/sign_in", viewName);
        verify(redirectAttributes, times(1)).addFlashAttribute("message", "Please sign in to view your cart.");
    }


    @Test
    public void testRenderCartPage_UserSignedIn_CartDoesNotExist() {
        User user = new User();
        user.setId(1L);

        when(session.getAttribute("user")).thenReturn(user);
        when(cartService.getCartById(user.getId())).thenReturn(Optional.empty());

        String viewName = frontendController.renderCartPage(model, session, redirectAttributes);

        assertEquals("redirect:/", viewName);
        verify(redirectAttributes, times(1)).addFlashAttribute("message", "Internal error occurred");
    }
}

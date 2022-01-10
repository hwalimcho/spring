package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,PageVo pageVo) throws Exception{
														//BoardVo boardVo
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);
		}
		
		boardList = boardService.SelectBoardList(pageVo);
		totalCnt = boardService.selectBoardCnt();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{
		
		
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo) throws Exception{
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();

		int total = 0 ;
		int listCnt = boardVo.getBoardVoList().size();
		int resultCnt = 0;
		//System.out.printf("listCnt : ", boardVo.getBoardVoList().size());

		for(int i=0; i<listCnt; i++ ) {

		BoardVo b = new BoardVo();
		b.setBoardTitle(boardVo.getBoardVoList().get(i).getBoardTitle());
		b.setBoardComment(boardVo.getBoardVoList().get(i).getBoardComment());

		
		resultCnt=boardService.boardInsert(b);
		total += resultCnt;
		}
		result.put("success", (total==listCnt)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
		
		//String titleList[] = boardVo.getBoardTitle().split(",");
		//String commentList[] = boardVo.getBoardComment().split(",");

	}
	
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdate.do", method = RequestMethod.GET)
	public String boardUpdate(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
	
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		int page = 1;
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("pageNo", page);
		model.addAttribute("board", boardVo);
		
		return "board/boardUpdate";
	}
	
	@RequestMapping(value = "/board/boardUpdateAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardUpdateAction(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardUpdate(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}

	
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardDelete.do", method = RequestMethod.GET)
	@ResponseBody
	public String boardDelete(Locale locale, BoardVo boardVo
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) 
			throws Exception{
		
			HashMap<String, String> result = new HashMap<String, String>();
			CommonUtil commonUtil = new CommonUtil();
		
			//BoardVo boardVo = new BoardVo();
		
			//boardVo = boardService.selectBoard(boardType,boardNum);
			//boardService.boardDelete(boardVo);
			
			//������ ������ �ٲ�
			
			int resultCnt = boardService.boardDelete(boardVo);
			
			result.put("success", (resultCnt > 0)?"It's successfully deleted.":"It's already deleted.");
			
			String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
			
			System.out.println("callbackMsg::"+callbackMsg);
			
			
			return callbackMsg;
			
			//return "redirect:/board/boardList.do";
		
	}


}

	





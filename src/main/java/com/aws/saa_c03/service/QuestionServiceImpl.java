package com.aws.saa_c03.service;

import com.aws.saa_c03.controller.dto.QuestionDto;
import com.aws.saa_c03.controller.dto.ResponsePage;
import com.aws.saa_c03.controller.dto.ResponseViewDto;
import com.aws.saa_c03.domain.Memo;
import com.aws.saa_c03.domain.Question;
import com.aws.saa_c03.domain.View;
import com.aws.saa_c03.dto.*;
import com.aws.saa_c03.repository.MemoRepository;
import com.aws.saa_c03.repository.QuestionRepository;
import com.aws.saa_c03.repository.ViewRepository;
import com.aws.saa_c03.service.converter.PageConverter;
import com.aws.saa_c03.service.converter.QuestionDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;
    private final ViewRepository viewRepository;
    private final QuestionDtoConverter questionDtoConverter;
    private final MemoRepository memoRepository;

    @Override
    @Transactional
    public int save(SaveQuestionDto dto) {
        Question q = Question.builder()
                .english(dto.getQuestion())
                .korean(dto.getTranslatedQuestion())
                .build();
        q = questionRepository.save(q);
        saveViews(dto, q);

        return q.getId();
    }

    @Override
    @Transactional
    public int update(SaveQuestionDto dto, int id) {
        Question q = questionRepository.findById(id).orElseThrow(()->new CustomException(StatusCode.NOT_FOUND_POST));
        List<View> view = q.getView();

        q.updateQuestion(dto.getQuestion(), dto.getTranslatedQuestion());
        view.forEach(v->{
            ViewDto first = dto.getViewList().stream().filter(viewDto -> viewDto.getId() == v.getId()).findFirst().orElseThrow(()->new CustomException(StatusCode.NOT_FOUND_POST));
            v.updateView(first.getEnglish(), first.getKorean(), first.getCollect());
        });

        q = questionRepository.save(q);
        viewRepository.saveAll(q.getView());
        return q.getId();
    }

    @Override
    @Transactional
    public Integer saveMemo(int questionId, String memo) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND_POST));
        Memo save;
        if (question.getMemo() != null){
            save = question.getMemo();
            save.updateMemo(memo);
        }else{
            save = memoRepository.save(new Memo(memo, question));
        }
        return save.getId();
    }

    private void saveViews(SaveQuestionDto dto, Question q) {
        List<View> views = getViews(dto, q);
        viewRepository.saveAll(views);
    }
    private static List<View> getViews(SaveQuestionDto dto, Question q) {
        return dto.getViewList().stream().map(v->new View(q, v.getEnglish(), v.getKorean(), v.getCollect())).toList();
    }

    @Override
    public QuestionDtoWithMemo findById(Integer id) {
        Question q = questionRepository.findByIdWithView(id).orElseThrow(()->new CustomException(StatusCode.NOT_FOUND_POST));
        List<ResponseViewDto> views = q.getView().stream().map(v->new ResponseViewDto(v.getId(), v.getEnglish(), v.getKorean(), v.isCollect())).toList();
        return QuestionDtoWithMemo.builder()
                .english(q.getEnglish())
                .korean(q.getKorean())
                .memo(q.getMemo()==null||q.getMemo().getMemo()==null?"":q.getMemo().getMemo())
                .id(q.getId())
                .views(views).build();
    }

    @Override
    public ResponsePage<QuestionDto> findAll(Pageable pageable) {
        Page<Question> questionList = questionRepository.findAll(pageable);
        return PageConverter.convert(questionList, questionDtoConverter);
    }

}
